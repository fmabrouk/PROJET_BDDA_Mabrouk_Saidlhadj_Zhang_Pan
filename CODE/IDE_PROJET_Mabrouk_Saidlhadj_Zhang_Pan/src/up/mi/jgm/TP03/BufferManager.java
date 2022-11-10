package up.mi.jgm.TP03;

import java.nio.ByteBuffer;
import up.mi.jgm.TP02.DBParams;
import up.mi.jgm.TP02.PageId;
import up.mi.jgm.TP02.DiskManager;

public class BufferManager {
	
	private static BufferManager g_instance1 = new BufferManager();
	
	public Frame [] pool; 
	public ListeChainee File_FrameMRU; 
	
	private BufferManager(){
		pool = new Frame [DBParams.frameCount];
		File_FrameMRU = new ListeChainee();
	}
	
	public  ByteBuffer GetPage(PageId pageID) {
		DiskManager disk =  DiskManager.getSingleton();
		for(int i=0; i< DBParams.frameCount; i++) {
			if(pool[i]==null) {
				System.out.println("AAAH");
				pool[i] = new Frame(pageID); 
				disk.readPage(pageID, pool[i].buffer); 
				pool[i].pin_Count++; 
				
				return pool[i].buffer;
			}
			else if (pool[i].pageID.equals(pageID)) {
				System.out.println("BBBH");
				pool[i].pin_Count ++; 
				if (pool[i].pin_Count == 1) {
					pool[i].chaine.supprimer();

				}
				return pool[i].buffer; 
			}
		}
		if (File_FrameMRU.frameSuiv != null) {
			System.out.println("CCCH");
			if(pool[File_FrameMRU.frameSuiv.index].flag_dirty) {
				disk.writePage(pool[File_FrameMRU.frameSuiv.index].pageID, pool[File_FrameMRU.frameSuiv.index].buffer);
			}
				Frame tmp = new Frame(pageID); 
				disk.readPage(pageID, tmp.buffer);
				pool[File_FrameMRU.frameSuiv.index] = tmp; 
				tmp.pin_Count++; 
				File_FrameMRU.frameSuiv.supprimer();
				return tmp.buffer; 
		}
		
		System.out.println(this);
		System.out.println("va retourner null + pageId: " + pageID);
		return null;
		 
	}
	
	public void freePage(PageId pageID,boolean valdirty) {
		for(int i = 0; i<DBParams.frameCount; i++) {
			System.out.println("pool["+i+"]" + pool[i]); 
			System.out.println("pageId : "+ pool[i].pageID); 
			System.out.println("pageId donne en param : "+ pageID); 
			if(pool[i].pageID.equals(pageID)) {
				if(pool[i].flag_dirty != true) {
					pool[i].flag_dirty=valdirty;
				}
				pool[i].pin_Count--;
				if (pool[i].pin_Count== 0) {
					//System.out.println("free :" + id +"index: " + i);
					ListeChainee tmp = new ListeChainee(i);
					pool[i].chaine = tmp; 
					File_FrameMRU.ajouter(tmp);
					//System.out.println(File_FrameMRU.frameSuiv); 
					//System.out.println(this);
				}
				break; 
			}
			 
		}
		
	
	}
	public void FlushAll(){
		DiskManager disk =  DiskManager.getSingleton();
        for (Frame element:pool){
            if (element != null && element.flag_dirty) {
                disk.writePage(element.pageID, element.buffer);
            }    
        }
	}
	public static BufferManager getSingleton() {
		return g_instance1;
	}

}


