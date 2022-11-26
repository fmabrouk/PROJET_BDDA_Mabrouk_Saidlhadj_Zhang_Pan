package up.mi.jgm.TP05;
import java.nio.ByteBuffer;

import up.mi.jgm.TP02.*;
import up.mi.jgm.TP03.*;
public class FileManager {
	
	private static FileManager fileManager= new FileManager(); 
	
	
	
	
    public  PageId createHeaderPage() {
        PageId nouvPage = DiskManager.getSingleton().AllocPage();
        System.out.println("nouvPage "+nouvPage);
        //PageId pIdFactice=new PageId(-1,0);
        //System.out.println("getPage "+nouvPage);
        BufferManager.getSingleton().bufferManager(2);
        ByteBuffer page = BufferManager.getSingleton().getPage(nouvPage);
        System.out.println("page " +page);
       
        System.out.println("freePage = "+nouvPage);
        BufferManager.getSingleton().freePage(nouvPage, true);
        
        return nouvPage;
    }
    
    
    
    public static FileManager getFileManager() {
    	return fileManager; 
    }
}
