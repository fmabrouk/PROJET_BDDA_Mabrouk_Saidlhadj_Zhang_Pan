package up.mi.jgm.TP05;
import up.mi.jgm.TP04.Record;
import up.mi.jgm.TP04.RelationInfo;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

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
   public PageId getFreeDataPageId (RelationInfo relInfo,int  sizeRecord) {
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
   
   
   //A completer
   public RecordId writeRecordToDataPage(Record record, PageId pageId) {
		
		RecordId rid= new RecordId();
		
		// Chercher le HeaderPage situé à PageIdx= 0 et charger son contenu
		PageId headerPage= new PageId(-1, 0);
		ByteBuffer bufferHeaderPage= BufferManager.getSingleton().getPage(headerPage);
		
		
		
		
		
		
		
		
		
		
		// Libérer la page et le headerPage avec le flag dirty = 1
		BufferManager.getSingleton().freePage(pageId, true);
		BufferManager.getSingleton().freePage(headerPage, true);

		// Forcé l'arrêt dès qu'on a fini d'écrire
		return rid;
	}
   
   
   //A completer
   public List<Record> getRecordsInDataPage(RelationInfo relInfo,PageId pageId) {
		List<Record> records= new ArrayList<Record>();
		
		// Récupère la page de données
		ByteBuffer buffer= BufferManager.getSingleton().getPage(pageId);

		
		

		// Parcourir le tableau de bitmaps
//		for(int i = 0; i<relInfo.getSlotCount(); i++) {
//			// Si le slot est occupé (1)
//			if(buffer.get(i) == 1) {
//				Record rec = new Record(relInfo);
//				rec.readFromBuffer(buffer, i);
//				records.add(rec);
//			}
//		}

		BufferManager.getSingleton().freePage(pageId, false);

		return records;
	}
   
   
   //A compléter
   public List<PageId> getAllDataPages(RelationInfo relInfo) {
	   List<PageId> listeDePagesIds = new ArrayList<PageId>();
	   return listeDePagesIds;
   }
   
   
   
   public RecordId InsertRecordIntoRelation(Record record) {
		RecordId rid = new RecordId();
		PageId pageId = getFreeDataPageId(record.getRelationInfo(),record.getWrittenSize());
		rid = writeRecordToDataPage(record, pageId);
		return rid;
	}
   
   
   
   
   public List<Record> GetAllRecords(RelationInfo relInfo) {
		List<Record> listeDeRecords = new ArrayList<Record>();

		// Chercher le HeaderPage situé à PageIdx= 0
		PageId headerPage= new PageId(relInfo.getHeaderPageId().getFileIdx(), 0);
		
		// Obtenir sa page
		ByteBuffer buffer= BufferManager.getSingleton().getPage(headerPage);
		

		int nbPage = buffer.getInt(0);

		// Parcourir toutes les pages
		for(int i = 1; i<=nbPage; i++) {	// i à utiliser comme pageIdx
			listeDeRecords.addAll(getRecordsInDataPage(relInfo,new PageId(relInfo.getHeaderPageId().getFileIdx(),i)));
		}

		BufferManager.getSingleton().freePage(headerPage, false);

		return listeDeRecords;
	}
	
    public static FileManager getFileManager() {
    	return fileManager; 
    }
}
