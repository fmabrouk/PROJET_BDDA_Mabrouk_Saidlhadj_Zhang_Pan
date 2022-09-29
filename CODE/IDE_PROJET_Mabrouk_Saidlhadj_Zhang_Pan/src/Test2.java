import java.io.IOException;
import java.nio.ByteBuffer;

public class Test2 {

	public static void main(String[] args) throws IOException {
		DBParams.DBPath = "C:\\Users\\fayez\\OneDrive\\Bureau\\BDDA_22\\PROJET_BDDA_Mabrouk_Saidlhadj_Zhang_Pan\\DB";
		DBParams.pageSize = 4096;
		DBParams.maxPagesPerFile = 4;
		//DiskManager disk = new DiskManager();
		DiskManager disk =  DiskManager.getSingleton();
		ByteBuffer br1 = ByteBuffer.allocate(DBParams.pageSize);
		PageId pageid1 = disk.AllocPage();
		PageId pageid2 = disk.AllocPage();
		/*byte[] bytes = new byte[10];
		ByteBuffer br1 = new ByteBuffer.wrap(bytes);*/
		br1.putChar('C');
		disk.writePage(pageid1,br1);
		 
		ByteBuffer br2 = ByteBuffer.allocate(DBParams.pageSize);
		disk.readPage(pageid2,br2);
		if(br2.getChar(0)=='C') {
			System.out.println("C'est bon");
		}
		else 
			System.out.print("On est des idiots");

	}

}
