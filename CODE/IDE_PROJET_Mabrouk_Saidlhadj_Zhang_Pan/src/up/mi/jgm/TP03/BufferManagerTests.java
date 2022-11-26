package up.mi.jgm.TP03;

import java.nio.ByteBuffer;
import up.mi.jgm.TP02.PageId;
import up.mi.jgm.TP02.DBParams;
import up.mi.jgm.TP02.DiskManager;

public class BufferManagerTests {

	public static void main(String[] args)  {
		
		DBParams.DBPath = "C:\\Users\\fayez\\OneDrive\\Bureau\\BDDA_22\\PROJET_BDDA_Mabrouk_Saidlhadj_Zhang_Pan\\DB";
		DBParams.pageSize = 4096;
		DBParams.maxPagesPerFile = 4;
		DBParams.frameCount = 2;
		
		//DiskManager disk =  DiskManager.getSingleton();
		BufferManager buffManag = BufferManager.getSingleton();
		buffManag.bufferManager(DBParams.frameCount);
		//buffManag.flushAll();
		 ByteBuffer br1 = ByteBuffer.allocate(DBParams.pageSize);
		 PageId pageid2 = DiskManager.getSingleton().AllocPage();
		 br1 = buffManag.getPage(pageid2);
		 buffManag.freePage(pageid2, false);
		 buffManag.flushAll();
        
		
		
		
		
		//PageId pageid2 = disk.AllocPage();
		//pageid1.toString();
		//PageId pageid2 = disk.AllocPage();
		 
		 //br1 = buffermanager.GetPage(pageid2);
		 //buffermanager.FreePage(pageid1,false);
		//buffermanager.freePage(pageid1, false);
		/*ByteBuffer br3 = ByteBuffer.allocate(DBParams.pageSize);*/ 
		
		 //ByteBuffer buffer = ByteBuffer.wrap(br1);
		 
		/*
        disk.writePage(pageid1,br3); 
        ByteBuffer br4 = buffermanager.GetPage(pageid1);
        System.out.println(br4.getChar());*/
	}

}
