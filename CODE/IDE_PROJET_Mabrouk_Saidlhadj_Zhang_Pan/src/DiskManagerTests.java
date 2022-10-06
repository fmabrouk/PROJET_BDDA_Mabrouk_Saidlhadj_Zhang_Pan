import java.io.IOException;
import java.nio.ByteBuffer;

	public class DiskManagerTests {

	    public static void main(String[] args) throws IOException {
	        DBParams.DBPath = "C:\\Users\\fayez\\OneDrive\\Bureau\\BDDA_22\\PROJET_BDDA_Mabrouk_Saidlhadj_Zhang_Pan\\DB";
	        DBParams.pageSize = 4096;
	        DBParams.maxPagesPerFile = 4;
	        
	        DiskManager disk =  DiskManager.getSingleton();
	        ByteBuffer br1 = ByteBuffer.allocate(DBParams.pageSize);
	        PageId pageid1 = disk.AllocPage();
	        System.out.println("Le nombre de pages allouées "+disk.GetCurrentCountAllocPages());
	        PageId pageid2 = disk.AllocPage();
	        System.out.println("Le nombre de pages allouées "+disk.GetCurrentCountAllocPages());
	        PageId pageid3 = disk.AllocPage();
	        System.out.println("Le nombre de pages allouées "+ disk.GetCurrentCountAllocPages());
	        disk.DeallocPage(pageid2);
	        System.out.println("Le nombre de pages allouées "+disk.GetCurrentCountAllocPages());
	        PageId pageid4 = disk.AllocPage(); 
	        System.out.println("Le nombre de pages allouées "+disk.GetCurrentCountAllocPages());
	        
	     
	        
	        br1.putChar('C');
	        disk.writePage(pageid1,br1);
	         
	        ByteBuffer br2 = ByteBuffer.allocate(DBParams.pageSize);
	        disk.readPage(pageid2,br2);
	        System.out.println(disk.GetCurrentCountAllocPages());
	        if(br2.getChar(0)=='C') {
	            System.out.println("C'est bon");
	        }
	        else 
	            System.out.print("On est des idiots");

	    }

}

