package up.mi.jgm.TP02;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.ByteBuffer;

import org.junit.jupiter.api.Test;

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
	    @Test
	    void testAllocPage() {
			DBParams.DBPath = "C:\\Users\\fayez\\OneDrive\\Bureau\\BDDA_22\\PROJET_BDDA_Mabrouk_Saidlhadj_Zhang_Pan\\DB";
	        DBParams.pageSize = 4096;
	        DBParams.maxPagesPerFile = 4;
	        DiskManager disk =  DiskManager.getSingleton();
	        //Cree une page avec la méthode AllocPage et verifie que les identifiants de la page sont corrects
	        PageId pageid1 = disk.AllocPage();
	        System.out.println("fichier : "+ pageid1.FileIdx+ " page : "+pageid1.PageIdx);
	        assert(pageid1.FileIdx==0 && pageid1.PageIdx==0); // La premiere page a les identifiants PageId(0,1)
	        PageId pageid2 = disk.AllocPage();
	        System.out.println("fichier 2 : "+ pageid2.FileIdx+ " page 2 : "+pageid2.PageIdx);
	        assert(pageid2.FileIdx==0 && pageid2.PageIdx==1);
	        PageId pageid3 = disk.AllocPage();
	        System.out.println("fichier 3 : "+ pageid3.FileIdx+ " page 3 : "+pageid3.PageIdx);
	        assert(pageid3.FileIdx==0 && pageid3.PageIdx==2);
	        PageId pageid4 = disk.AllocPage();
	        System.out.println("fichier 4 : "+ pageid4.FileIdx+ " page 4 : "+pageid4.PageIdx);
	        assert(pageid4.FileIdx==0 && pageid4.PageIdx==3);
	        
	        PageId pageid5 = disk.AllocPage();
	        System.out.println("fichier 5 : "+ pageid5.FileIdx+ " page 5 : "+pageid5.PageIdx);
	        assert(pageid5.FileIdx==1 && pageid5.PageIdx==0);
	        
		}
	    
	    @Test
	    void testDeallocPage() {
			DBParams.DBPath = "C:\\Users\\fayez\\OneDrive\\Bureau\\BDDA_22\\PROJET_BDDA_Mabrouk_Saidlhadj_Zhang_Pan\\DB";
	        DBParams.pageSize = 4096;
	        DBParams.maxPagesPerFile = 4;
	        DiskManager disk =  DiskManager.getSingleton();
			PageId pageid1 = disk.AllocPage();
			disk.DeallocPage(pageid1);
			
		}

	    @Test
        void testGetPage() {
            fail("Not yet implemented");
        }

        @Test
        void testIndexOfFrame() {
            fail("Not yet implemented");
        }

        @Test
        void testFreePage() {
            fail("Not yet implemented");
        }

        @Test
        void testFlushAll() {
            fail("Not yet implemented");
        }

        @Test
        void testGetSingleton() {
            fail("Not yet implemented");
        }


}

