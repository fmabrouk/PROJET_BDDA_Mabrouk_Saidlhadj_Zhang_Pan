import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

/*import java.io.File;
import java.io.FileReader;
import java.io.IOException;*/

public class test1 {

	
		public static void main(String[] args) throws IOException {
			DBParams.DBPath = "C:\\Users\\fayez\\OneDrive\\Bureau\\BDDA_22\\PROJET_BDDA_Mabrouk_Saidlhadj_Zhang_Pan\\DB";
			DBParams.pageSize = 4096;
			DBParams.maxPagesPerFile = 4;
			DiskManager disk = new DiskManager();
			/*File fileTest = new File("Test.txt");
			System.out.println(fileTest.getAbsolutePath());
			DiskManager disk = new DiskManager();
			try {
				System.out.println(disk.GetLength(fileTest));
			} catch (IOException e) {
				e.printStackTrace();
			}*/
			int max = 4;
			for (int i = 0;i<max;++i) {
				disk.AllocPage();
			}
			
			/*
			 **/
			ByteBuffer b1;
			ByteBuffer b2;
			//ByteBuffer buffer = ByteBuffer.wrap(bytes);
			//PageId page = new PageId(1,2);
			/*
			disk.readPage(id,b1);
			disk.writePage(id,b2);*/
		}
	}


