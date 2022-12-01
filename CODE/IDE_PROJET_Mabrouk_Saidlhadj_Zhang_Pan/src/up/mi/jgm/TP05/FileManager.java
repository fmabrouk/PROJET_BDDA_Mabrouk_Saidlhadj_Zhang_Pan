package up.mi.jgm.TP05;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import up.mi.jgm.TP04.*;
import up.mi.jgm.TP04.Record;
import up.mi.jgm.TP02.*;
import up.mi.jgm.TP03.*;
public class FileManager {
	
	private static FileManager fileManager= new FileManager(); 
	
	
	
	public  PageId createNewHeaderPage() {
        PageId nouvPage = DiskManager.getSingleton().AllocPage();
        BufferManager.getSingleton().bufferManager(DBParams.frameCount);
        ByteBuffer page = BufferManager.getSingleton().getPage(nouvPage);
        page.putInt(0);
        BufferManager.getSingleton().freePage(nouvPage, true);
        return nouvPage;
    }
    
	public PageId getFreeDataPageId (RelationInfo relInfo,int  sizeRecord) {
	       PageId dataPageId= null;
	        
	        // Chercher le HeaderPage situé à PageIdx= 0
	        PageId headerPage= new PageId(relInfo.getHeaderPageId().getFileIdx(), 0);
	        
	        // Obtenir sa page
	       
	        BufferManager.getSingleton().bufferManager(DBParams.frameCount);
	        ByteBuffer buffer= BufferManager.getSingleton().getPage(headerPage);
	        
	        
	        // Récupérer le nombre de page dans HeaderPage
	        int nbPage= buffer.getInt(0);
	        
	        // Parcourir HeaderPage à la recherche d'une page avec un slot restant
	        PageId page=null;
			 for(int i=0; i<nbPage ; i++) {
				 if(sizeRecord <= buffer.get(Integer.BYTES + i*2*Integer.BYTES)) {
					 int FileIdx = buffer.getInt((Integer.BYTES + i*Integer.BYTES)*i);
					 int PageIdx = buffer.getInt((Integer.BYTES +i*3*Integer.BYTES )*i);
					 dataPageId = new PageId(FileIdx,PageIdx);
				 } 
		        }

	        if(dataPageId == null)
	            dataPageId = addDataPage(relInfo);

	        // Libérer la page avec le flag dirty = 0
	        BufferManager.getSingleton().freePage(headerPage, false);
	        
	        return dataPageId;
	   }
	
    public PageId addDataPage(RelationInfo relInfo) {
    			// Rajouter une page du fichier disque correspondant
    			PageId pageId= DiskManager.getSingleton().AllocPage();
    			 BufferManager.getSingleton().bufferManager(DBParams.frameCount);
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

    			int nbOctetsLibre = buffer.getInt(0);
    			
    			//Ajoute l'entrée de la DataPage à la HeaderPage
    			buffer.putInt((Integer.BYTES + buffer.getInt(0)*Integer.BYTES),pageId.FileIdx);
    			buffer.putInt(Integer.BYTES +buffer.getInt(0)*3*Integer.BYTES,pageId.PageIdx);
    			buffer.putInt(Integer.BYTES + buffer.getInt(0)*2*Integer.BYTES, nbOctetsLibre);
    					
    			// Ajout du nombre de slot disponible pour la page
    			
    			DataPage data = new DataPage(bufferPageAjoutee);
    			
    			bufferPageAjoutee.putInt(pageId.getPageIdx()*Integer.BYTES, data.getNbEntrySlot());
    			
    			// Actualiser le nombre de page
    			buffer.putInt(0, pageId.getPageIdx());
    				
    			// Libérer la page avec le flag dirty = 1
    			BufferManager.getSingleton().freePage(headerPage,true);
    			
    			
    			return pageId;
    }
    
    
 public RecordId writeRecordToDataPage(Record record, PageId pageId) {
        
        RecordId rid= new RecordId();
        
        // Chercher le HeaderPage situé à PageIdx= 0 et charger son contenu
        PageId headerPage= new PageId(record.getRelInfo().getHeaderPageId().getFileIdx(), 0);
        BufferManager.getSingleton().bufferManager(DBParams.frameCount);
        ByteBuffer bufferHeaderPage= BufferManager.getSingleton().getPage(headerPage);
        // Récupère la page de données identifiée par le pageId
        ByteBuffer buffer = BufferManager.getSingleton().getPage(pageId);
        
        int posInitiale = DBParams.pageSize-2*Integer.BYTES;
        int numRecord = bufferHeaderPage.getInt(DBParams.pageSize-2*Integer.BYTES);
        
        writeTailleRec(posInitiale, buffer, numRecord);
        writePosRec(posInitiale, buffer, numRecord);
        
        int position = bufferHeaderPage.getInt(DBParams.pageSize-2*Integer.BYTES);
        record.writeToBuffer(buffer, position);
        
        // Libérer la page et le headerPage avec le flag dirty = 1
        BufferManager.getSingleton().freePage(pageId, true);
        BufferManager.getSingleton().freePage(headerPage, true);

        // Forcé l'arrêt dès qu'on a fini d'écrire
        return rid;
    }
 
 public void writeTailleRec(int posInitiale, ByteBuffer buffer, int numRecord) {
	 buffer.putInt(posInitiale-2*Integer.BYTES*numRecord);
	 }
 
 public void writePosRec(int posInitiale, ByteBuffer buffer, int numRecord) {
	 buffer.putInt(posInitiale-2*2*Integer.BYTES*numRecord);
	 }
 
 public List<Record> getRecordsInDataPage(RelationInfo relInfo,PageId pageId) {
     List<Record> records= new ArrayList<Record>();
     
     // Récupère la page de données
     BufferManager.getSingleton().bufferManager(DBParams.frameCount);
     ByteBuffer buffer= BufferManager.getSingleton().getPage(pageId);
     int nbRecords = buffer.getInt(DBParams.pageSize-2*Integer.BYTES);
     int posInitiale = DBParams.pageSize-2*Integer.BYTES;
     for(int i=0; i<nbRecords; i++) {
    	 ArrayList<String> values = new ArrayList<String>();
    	 Record rec = new Record(relInfo, values);
    	 rec.readFromBuffer(buffer, readPosRec(posInitiale, buffer, i));
    	 records.add(rec);
     }
     
 
     BufferManager.getSingleton().freePage(pageId, false);

     return records;
 }
 
 public List<PageId> getAllDataPages(RelationInfo relInfo) {
     List<PageId> listeDePagesIds = new ArrayList<PageId>();
     
     PageId headerPage= new PageId(relInfo.getHeaderPageId().getFileIdx(), 0);
     BufferManager.getSingleton().bufferManager(DBParams.frameCount);
     ByteBuffer bufferHeaderPage= BufferManager.getSingleton().getPage(headerPage);
     
     int nbPagesData = bufferHeaderPage.getInt(0);
     
     for(int i=0; i<nbPagesData; i++) {
    	 int FileIdx = bufferHeaderPage.getInt((Integer.BYTES + i*Integer.BYTES)*i);
		 int PageIdx = bufferHeaderPage.getInt((Integer.BYTES +i*3*Integer.BYTES )*i);
		 PageId p = new PageId(FileIdx, PageIdx);
		 listeDePagesIds.add(p);
     }
     
     return listeDePagesIds;
 }
 
 public int readTailleRec(int posInitiale, ByteBuffer buffer, int numRecord) {
	return buffer.getInt(posInitiale-2*Integer.BYTES*numRecord);
	 }
 
 public int readPosRec(int posInitiale, ByteBuffer buffer, int numRecord) {
	 return buffer.getInt(posInitiale-2*2*Integer.BYTES*numRecord);
	 }
 public RecordId InsertRecordIntoRelation(Record record) {
     RecordId rid = new RecordId();
     PageId pageId = getFreeDataPageId(record.getRelInfo(),record.getWrittenSize());
     rid = writeRecordToDataPage(record, pageId);
     return rid;
 }
 
 public List<Record> GetAllRecords(RelationInfo relInfo) {
     List<Record> listeDeRecords = new ArrayList<Record>();

     // Chercher le HeaderPage situé à PageIdx= 0
     PageId headerPage= new PageId(relInfo.getHeaderPageId().getFileIdx(), 0);
     
     // Obtenir sa page
     BufferManager.getSingleton().bufferManager(DBParams.frameCount);
     ByteBuffer buffer= BufferManager.getSingleton().getPage(headerPage);
     

     int nbPage = buffer.getInt(0);

     // Parcourir toutes les pages
     for(int i = 1; i<=nbPage; i++) {    // i à utiliser comme pageIdx
         listeDeRecords.addAll(getRecordsInDataPage(relInfo,new PageId(relInfo.getHeaderPageId().getFileIdx(),i)));
     }

     BufferManager.getSingleton().freePage(headerPage, false);

     return listeDeRecords;
 }
 
    public static FileManager getFileManager() {
    	return fileManager; 
    }
    
   
}