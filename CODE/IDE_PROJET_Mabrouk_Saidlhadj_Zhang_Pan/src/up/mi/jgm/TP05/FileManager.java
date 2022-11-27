package up.mi.jgm.TP05;
import up.mi.jgm.TP04.Record;
import java.nio.ByteBuffer;
import java.util.ArrayList;

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
    	PageId nouvPage = DiskManager.getSingleton().AllocPage();
    	PageId ancienPage = relInfo.getHeaderPageId();
    	// A completer : fonction qui recupere une page d'une pageId
    	ByteBuffer page = BufferManager.getSingleton().getPage(nouvPage);
    	DataPage dataPage = new DataPage(page);
    	dataPage.setNbEntrySlot(0);
    	BufferManager.getSingleton().freePage(ancienPage, true);
    	return nouvPage;
    }
    
    public PageId getFreeDataPageId(RelationInfo relInfo,int sizeRecord) {		
		PageId dataPageId= null;
		
		// Chercher le HeaderPage situé à PageIdx= 0
		PageId headerPage= new PageId(relInfo.getHeaderPageId().getFileIdx(), 0);
		
		// Obtenir sa page
		ByteBuffer buffer= BufferManager.getSingleton().getPage(headerPage);
		
		// Récupérer le nombre de page dans HeaderPage
		int nbPage= buffer.getInt(0);
		
		// Parcourir HeaderPage à la recherche d'une page avec un slot restant
		boolean t = false;
		for (int i=1; i<=nbPage && !t; i++) {
			// Si le nombre de slot n'est pas 0 alors on retourne cette page
			if (buffer.getInt(i*4) > 0) {	// Multiplication par 4 : taille d'un int (4 octets)
				dataPageId= new PageId(relInfo.getHeaderPageId().getFileIdx(), i);
				t = true;
			}
		}

		if(dataPageId == null)
			dataPageId = addDataPage(relInfo);

		// Libérer la page avec le flag dirty = 0
		BufferManager.getSingleton().freePage(headerPage, false);
		
		return dataPageId;
	}
    
    /**
	 * Ecrire l'enregistrement record dans la page de données identifiée par pageId
	 * 
	 * @param record Le record en question
	 * @param pageId Le pageId en question
	 * @return L'identifiant de l'enregistrement
	 */
	public RecordId writeRecordToDataPage(Record record, PageId pageId) {
		
		RecordId rid= new RecordId();
		
		// Chercher le HeaderPage situé à PageIdx= 0 et charger son contenu
		PageId headerPage= new PageId(DiskManager.getSingleton().AllocPage().getFileIdx(), 0);
		ByteBuffer bufferHeaderPage= BufferManager.getSingleton().getPage(headerPage);
		 
		
		// Récupère la page de données identifiée par pageId
		ByteBuffer buffer= BufferManager.getSingleton().getPage(pageId);
		
		// Nombre de bitmap nécessaire dans une page
		DataPage data = new DataPage(buffer);
		int slot = data.getNbEntrySlot();
		
		// Récupère tous les données des slots 
		

		// Parcourir le tableau de bitmap
		boolean placer = false;
		
			// Si bitmap = 0 alors c'est une case libres
			
				
				// Ecrire le record dans la page de données libre
				
				
				

				// Actualiser le nombre de slot restant de la page
				int index = pageId.getPageIdx() * 4;
				int value = bufferHeaderPage.getInt(index);
				bufferHeaderPage.putInt(index, --value);

				// Actualise l'identifiant du record
				//rid.setSlotIdx(i);
				rid.setPageId(pageId);

				placer = true;
			
		

		// Libérer la page et le headerPage avec le flag dirty = 1
		BufferManager.getSingleton().freePage(pageId, true);
		BufferManager.getSingleton().freePage(headerPage, true);

		// Forcé l'arrêt dès qu'on a fini d'écrire
		return rid;
	}
	
	
	//A completer
	public Record [] getRecordsInDataPage(RelationInfo relInfo, PageId pageId) {
    	ArrayList<Record> recordList = new ArrayList<Record>();
    	//Record [] recordList = new Record[relInfo.slotCount]; 
    	//System.out.println("getpage"+pageId); 
    	ByteBuffer page = BufferManager.getSingleton().getPage(pageId); 
    	page.position(Integer.BYTES*4); 
    
    	//System.out.println("freePage"+pageId);
    	BufferManager.getSingleton().freePage(pageId, false);
    	return recordList.toArray(Record []::new);
    }
	
    public static FileManager getFileManager() {
    	return fileManager; 
    }
}
