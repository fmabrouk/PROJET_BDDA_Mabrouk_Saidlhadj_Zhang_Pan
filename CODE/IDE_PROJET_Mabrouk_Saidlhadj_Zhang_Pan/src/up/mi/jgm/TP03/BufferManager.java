package up.mi.jgm.TP03;

import java.io.IOException;
import java.nio.ByteBuffer;
//import up.mi.jgm.TP02.DBParams;
import up.mi.jgm.TP02.PageId;
import up.mi.jgm.TP02.DiskManager;
//import java.util.ArrayList;
	
public class BufferManager {
	
   private static BufferManager g_instance1 = new BufferManager();
	
	private static Frame[] frames;

	public  void bufferManager(int size) {
		frames = new Frame[size];
		for (int i = 0; i <size; i++)
			frames[i] = new Frame();

	}

	/**
	 * Fonction getPage puts a page to current frames if the clock algorithm allows
	 * it
	 * 
	 * @param pageToRead
	 * @return
	 * @throws IOException
	 */
	public  ByteBuffer getPage(PageId pageToRead)  {
		ByteBuffer buffer = ByteBuffer.allocate((int) 4096);//DBParams.pageSize
		int i;
		boolean chosen = false;

		i = indexOfFrame(pageToRead);
		//System.out.println("i = "+i);
		if (i != -1) {
			frames[i].setPinCount(true);
			
			//frames[i].toString();
			return frames[i].getBuffer();
		}
		
		for (i = 0; i < frames.length; i++) {
			
			if (frames[i].getPageId() == null) {
				frames[i].setPageId(pageToRead);
				DiskManager.getSingleton().readPage(pageToRead, buffer);
				frames[i].setBuffer(buffer);
				frames[i].setPinCount(true);
				//System.out.println("erreur");
				
				return frames[i].getBuffer();
			}
		}
		
		i=0;

		do

		{
			if (!frames[i].getPinCount() && frames[i].getRefBit())
				frames[i].setRefBit(false);
			else if (!frames[i].getPinCount() && !frames[i].getRefBit())
				chosen = true;
			i = (chosen) ? i : i + 1;
			i = (i == frames.length) ? 0 : i;
		} while (!chosen);

		if (frames[i].isDirty())
			DiskManager.getSingleton().writePage(frames[i].getPageId(), frames[i].getBuffer());
			

		frames[i].setPageId(pageToRead);
		DiskManager.getSingleton().readPage(pageToRead, buffer);
		frames[i].setBuffer(buffer);
		frames[i].setPinCount(true);
		frames[i].setRefBit(false);
		frames[i].setDirty(false);
		//frames[i].toString();
		return frames[i].getBuffer();
	}

	public  void freePage(PageId pageToFree, boolean isDirty) {
		int index = indexOfFrame(pageToFree);

		if (index != -1) {
			if (isDirty)
				frames[index].setDirty(true);
			frames[index].setPinCount(false);
			frames[index].setRefBit(true);
		} else
			System.out.println("The page to free is not in the buffer");
	}

	public  void flushAll() {

		for (int i = 0; i < frames.length; i++) {
			if (frames[i].isDirty())
				DiskManager.getSingleton().writePage(frames[i].getPageId(), frames[i].getBuffer());
			if (frames[i].getPinCount())
				System.err.println("A page was not liberated");
		}
		
		bufferManager(frames.length);
	}

	public  void writePage(PageId pageToWrite, ByteBuffer bufferToWrite) {
		int index = indexOfFrame(pageToWrite);
		
		if (index != -1)
			frames[index].setBuffer(bufferToWrite);
		else
			System.out.println("The page to write is not in the buffer");
	}

	public  int indexOfFrame(PageId pageToFind) {
		int i;
		for (i = 0; i < frames.length; i++)
			if (frames[i].getPageId() != null && frames[i].getPageId().equals(pageToFind))
				return i;

		return -1;
	}
	
//	    public ArrayList<Frame> pool;
//	    public BufferManager(){
//	        pool=new ArrayList<Frame>(DBParams.frameCount);
//	        //DBParams.frameCount = size;
//	    }
//
//	    public byte [] getPage(PageId id) {
//	    	System.out.println("pool.size() = "+pool.size());
//	        int tmp = contient(id);
//	        System.out.println("tmp = "+tmp);
//        	
//
//	        if (tmp == -1) {
//	        	
//	        	System.out.println("DBParams.frameCount = "+DBParams.frameCount); 
//	            if (pool.size()==DBParams.frameCount) {
//	            	//affiche();
//	            	System.out.println("hhh");
//	            	//System.out.println("pool.get(remplacementMRU(id)).buffer = "+pool.get(remplacementMRU(id)).buffer);
//	                return pool.get(remplacementMRU(id)).buffer;
//	            }
//	            else {
//	            	
//	                pool.set(pool.size(), new Frame(id));
//	                nouvElem(pool.size()-1);
//	                ByteBuffer buffer = ByteBuffer.wrap(pool.get(pool.size()-1).buffer, 0,pool.get(pool.size()-1).buffer.length);
//	                DiskManager.getSingleton().readPage(pool.get(pool.size()-1).id,buffer );
//	                return pool.get(pool.size()-1).buffer;
//	            }
//	        }
//	        nouvElem(tmp);
//	        pool.get(tmp).temps = 0;
//	        return pool.get(tmp).buffer;
//	    }
//	    /*
//	    private int remplacementLRU(PageId id) {
//	    	int index = -1;
//	    	LRU lru = new LRU(DBParams.frameCount);
//	    	
//	    	return index;
//	    	
//	    }*/
//	    private int remplacementMRU(PageId id) {
//	    	//ByteBuffer buffer = ByteBuffer.allocate(10);
//	    	
//	        int min =DBParams.frameCount;
//	        int index=-1; //attention à ça
//	        for(int i = 0; i<DBParams.frameCount; i++){
//	            if (pool.get(i).pin_count == 0 && pool.get(i).temps <min) {
//	            index = i;
//	            }
//	        }
//	        if(pool.get(index).dirty) {
//	        	//byte[] bytes = new byte[10];
//		    	//ByteBuffer buffer = ByteBuffer.wrap(pool.get(index).buffer);
//	        	ByteBuffer buffer = ByteBuffer.wrap(pool.get(index).buffer, 0,pool.get(index).buffer.length);
//	            DiskManager.getSingleton().writePage(id, buffer);
//	        }
//	        nouvElem(index);
//	        ByteBuffer buffer = ByteBuffer.wrap(pool.get(index).buffer, 0,pool.get(index).buffer.length);
//	        DiskManager.getSingleton().readPage(pool.get(index).id, buffer);
//	        return index ;   
//	    }
//	        
//	    private int contient(PageId id) {
//	        for(int i = 0; i < DBParams.frameCount; i++) {
//	        	System.out.println("pool.get(i) = " + pool.get(i));
//	            if (pool.get(i).id.equals(id)){
//	                return i;
//	            }
//	        }
//	        return -1;
//	    }
//	        
//	    private void nouvElem(int index) {
//	        for(int i = 0; i <DBParams.frameCount; i++) {
//	            if(i!=index) {
//	                pool.get(i).temps++;
//	            }
//	            else {
//	                pool.get(index).pin_count++;
//	            }
//	        }
//	    }
//
//
//
//	    public void FreePage(PageId id, boolean valdirty){
//	       for (Frame element:pool){
//	           if (element.id.equals(id)){
//	               element.pin_count--;
//	            }
//	            if (!element.dirty) {
//	                element.dirty=valdirty;
//	            }
//	       } 
//	    }
//
//	    public void FlushAll(){
//	        for (Frame element:pool){
//	            if (element.dirty) {
//	            	ByteBuffer buffer = ByteBuffer.wrap(element.buffer, 0,element.buffer.length);
//	                DiskManager.getSingleton().writePage(element.id, buffer); 
//	            }
//	        }
//	        pool.clear();
//	    }
//	
//	 
	
	public static BufferManager getSingleton() {
		return g_instance1;
	}

}



