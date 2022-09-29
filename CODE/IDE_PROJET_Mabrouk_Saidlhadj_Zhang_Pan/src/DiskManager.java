import java.io.File;
//import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
//import java.nio.ByteBuffer;

public class DiskManager {
	
	private static DiskManager g_instance = new DiskManager();
	
	private int Npage = 0;
	private int taillePage = 0;
	
	public PageId AllocPage() {

		
		     /* File dir  = new File("../../DB");
		      File[] liste = dir.listFiles();
		      for(File item : liste){
		    	  if(GetLength(item)<4096*4) {
		    		  System.out.println("Taille maximale pas atteinte");
		    		  int indice = GetIndice(liste);
		    		  PageId page = new PageId(indice,);
		    		  return page;
		    	  }*/
		/*
		File dir  = new File(DBParams.DBPath);
        File[] liste = dir.listFiles();
        int p=0;
        for(File item : liste){
            if(GetLength(item)+DBParams.pageSize<DBParams.pageSize*4) {
                for(int i=0;i<=4;i++){
                    if(GetLength(item)<DBParams.pageSize*(i+1)){
                        p=i;
                    }
                }
                System.out.println("Taille maximale pas atteinte");
               // int indice = GetIndice(liste);

               // PageId page = new PageId(indice,p);
                
            }
            */
		    		  
		     
		  
		     
		     /* 
		     int numFichier = GetIndice(liste); 
		     numFichier++;
		     File fileCreated = new File(DBParams.DBPath+"\\F"+numFichier+".bdda");
		     try {
				if(fileCreated.createNewFile()) {
					 System.out.println("Fichier bien créé");
				 }
				 else {
				 System.out.println("Fichier pas créé");
				 }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		     //System.out.println(fileCreated.getAbsolutePath());
        } 
        /*return null;*/
        
		
		
		++taillePage;
		
		if(taillePage>=DBParams.maxPagesPerFile ) {
			++Npage;
			taillePage=0;
			
			File dir  = new File(DBParams.DBPath);
	        File[] liste = dir.listFiles();
	        
			int numFichier = GetIndice(liste); 
		     numFichier++;
		     File fileCreated = new File(DBParams.DBPath+"\\F"+numFichier+".bdda");
		     try {
				if(fileCreated.createNewFile()) {
					 System.out.println("Fichier bien créé");
				 }
				 else {
				 System.out.println("Fichier pas créé");
				 }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		     //System.out.println(fileCreated.getAbsolutePath());
		}
		
		//System.out.println("taille de la page "+taillePage);
		//System.out.println("identifiant de la page " +Npage);
		PageId ID = new PageId(Npage,taillePage);
		return ID;
		
	}
	
	private int GetIndice(File[] liste) {
		
		String nameLast = liste[liste.length-1].getName();
	    // System.out.println(nameLast);
	      
	     String[] name = nameLast.split("F");
	     String[] name2 = name[1].split("[.]");
	     //System.out.println(name2[0]);
	     int numFichier = Integer.parseInt(name2[0]);
		return numFichier;
	}
	
	public long GetLength(File file) throws IOException {
		RandomAccessFile r = new RandomAccessFile(file, "rwd");
		r.writeUTF("Test");
		long length = r.length();
		r.close();
		return length;
	}
	
	/**
	 * 
	 * @param pageID
	 * @param buff
	 */
	public void readPage(PageId pageID, ByteBuffer buffer) {
        //�crire sur buffer le contenu disque de la page identifi�e par l'argument pageId. 
      /*  try {
            RandomAccessFile file = new RandomAccessFile(DBParams.DBPath+"/F"+pageID.FileIdx+".bdda", "r" );
            file.seek((pageID.PageIdx)*DBParams.pageSize);
            //file.read(buff, 0, DBParams.pageSize); 
            file.close();
        }catch (IOException e) {
            System.out.println("Erreur E/S");
            e.printStackTrace();
        }*/
		if (pageID != null) {

			buffer.position(0);
			File file = new File(DBParams.DBPath + pageID.FileIdx + ".rf");
			
			try {
				RandomAccessFile raf = new RandomAccessFile(file, "r");
				raf.seek(DBParams.pageSize * pageID.FileIdx);
				for (int i = 0; i <DBParams.pageSize; i++)
					buffer.put(raf.readByte());

				raf.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			

		}
    }
	
	public void writePage(PageId pageID, ByteBuffer buffer) {    
      /*  try {
            RandomAccessFile file = new RandomAccessFile(DBParams.DBPath+"/F"+pageID.FileIdx+".bdda", "rw");
            file.seek((pageID.PageIdx)*DBParams.pageSize);
            //file.write(buff, 0, DBParams.pageSize);
            file.close();
        }catch (IOException e) {
            System.out.println("Erreur E/S");
        }*/
		if (pageID != null) {

			buffer.position(0);
			File file = new File(DBParams.DBPath + pageID.FileIdx + ".rf");
			
			try {
				RandomAccessFile raf = new RandomAccessFile(file, "rw");
				raf.seek(DBParams.pageSize * pageID.FileIdx);
				for (int i = 0; i < DBParams.pageSize; i++) {
					raf.writeByte(buffer.get());
				}

				raf.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			

		}
    }
	
	public static DiskManager getSingleton() {
		return g_instance;
	}
}
