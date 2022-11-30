package up.mi.jgm.TP05;

import java.nio.ByteBuffer;


import up.mi.jgm.TP04.*;

import up.mi.jgm.TP02.*;
import up.mi.jgm.TP03.*;
public class FileManager {
	
	private static FileManager fileManager= new FileManager(); 
	
	
	
	public  PageId createNewHeaderPage() {
        PageId nouvPage = DiskManager.getSingleton().AllocPage();
        BufferManager.getSingleton().bufferManager(2);
        ByteBuffer page = BufferManager.getSingleton().getPage(nouvPage);
        page.putInt(0);
        BufferManager.getSingleton().freePage(nouvPage, true);
        return nouvPage;
    }
    
    
    public PageId addDataPage(RelationInfo relInfo) {
    			// Rajouter une page du fichier disque correspondant
    			PageId pageId= DiskManager.getSingleton().AllocPage();
    			BufferManager.getSingleton().bufferManager(2);
    			ByteBuffer bufferPageAjoutee= BufferManager.getSingleton().getPage(pageId);
    			
    			/* **** Actualiser les informations de la page ajouté **** */
    			bufferPageAjoutee.putInt(0);
    			
    			// Libérer la page avec le flag dirty = 1
    			BufferManager.getSingleton().freePage(pageId,true);
    			
    			/* **** Actualiser les informations de la Header Page******** */

    			// Chercher le HeaderPage situé à PageIdx= 0
    			PageId headerPage= new PageId(relInfo.getHeaderPageId().getFileIdx(), 0);
    				
    			// Obtenir sa page
    			ByteBuffer buffer= BufferManager.getSingleton().getPage(headerPage);

    			
    					
    			// Ajout du nombre de slot disponible pour la page
    			DataPage data = new DataPage(buffer);
    			
    			buffer.putInt(pageId.getPageIdx()*4, data.getNbEntrySlot());
    			
    			// Actualiser le nombre de page
    			buffer.putInt(0, pageId.getPageIdx());
    				
    			// Libérer la page avec le flag dirty = 1
    			BufferManager.getSingleton().freePage(headerPage,true);
    			
    			
    			return pageId;
    }
    //A completer
//    public PageId getFreeDataPageId (RelationInfo relInfo,int  sizeRecord) {
//    	
//    }
   
	
    public static FileManager getFileManager() {
    	return fileManager; 
    }
}
