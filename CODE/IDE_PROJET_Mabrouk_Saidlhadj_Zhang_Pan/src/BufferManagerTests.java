import java.nio.ByteBuffer;

public class BufferManagerTests {

	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		DBParams.DBPath = "C:\\Users\\fayez\\OneDrive\\Bureau\\BDDA_22\\PROJET_BDDA_Mabrouk_Saidlhadj_Zhang_Pan\\DB";
		DBParams.pageSize = 4096;
		DBParams.maxPagesPerFile = 4;
		DBParams.frameCount = 2;
		BufferManager buffermanager =  BufferManager.getSingleton();
		
		//DiskManager disk = new DiskManager();
		DiskManager disk =  DiskManager.getSingleton();
		/*Frame frame = new Frame();
		 */ 
		
		
		ByteBuffer br1 = ByteBuffer.allocate(DBParams.pageSize);
		PageId pageid1 = disk.AllocPage();
		//PageId pageid2 = disk.AllocPage();
		//pageid1.toString();
		//PageId pageid2 = disk.AllocPage();
		 br1 = buffermanager.GetPage(pageid1);
		 //br1 = buffermanager.GetPage(pageid2);
		 buffermanager.freePage(pageid1,false);
		//buffermanager.freePage(pageid1, false);
		/*ByteBuffer br3 = ByteBuffer.allocate(DBParams.pageSize);*/ 
		
       /* br1.putChar('D');*/
		
		/*
        disk.writePage(pageid1,br3); 
        ByteBuffer br4 = buffermanager.GetPage(pageid1);
        System.out.println(br4.getChar());*/
	}

}
