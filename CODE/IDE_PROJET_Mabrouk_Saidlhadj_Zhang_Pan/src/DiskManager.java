import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class DiskManager {
	
	private static DiskManager g_instance = new DiskManager();
	
	private int Npage = 0;
	private int taillePage = 0;
	private ArrayList<PageId> pagesAllouees = new ArrayList<PageId>();
	private ArrayList<PageId> pagesDesallouees = new ArrayList<PageId>();
	
	/**
	 * fonction permet d'allouer la place en mémoire pour le pageId
	 * @return renvoie la pageId allouée
	*/
	public PageId AllocPage() {       
		if(pagesDesallouees.size()!=0) {
			PageId p1 = pagesDesallouees.get(0);
			pagesAllouees.add(pagesDesallouees.get(0));
			pagesDesallouees.remove(0);
			return p1;
		}
		PageId ID2 = new PageId(Npage,taillePage);
		pagesAllouees.add(ID2);
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
				e.printStackTrace();
			}
		}
		PageId ID = new PageId(Npage,taillePage);
		return ID;
	}
	
	/*
	 * fonction permet de desallouer la place en mémoire pour la pageId
	 * @param pageID de type PageId
	*/
	public void DeallocPage(PageId pageID) {
	    pagesDesallouees.add(pageID);
	    for(int i=0; i<pagesAllouees.size(); i++) {
    	  if(pagesAllouees.get(i).getFileIdx()==pageID.getFileIdx() && pagesAllouees.get(i).getPageIdx()==pageID.getPageIdx() ) {
    		  System.out.println("Page supprimée :" + pagesAllouees.get(i).getFileIdx()+ " "+ pagesAllouees.get(i).getPageIdx());
    		  pagesAllouees.remove(i);
    	  }
      }
	}
	
	/*
	 * fonction permet de récuperer l'indice de fichier dans la liste
	 * @param liste de type File[]
	 * @return renvoie l'indice
	*/
	private int GetIndice(File[] liste) {
		
		String nameLast = liste[liste.length-1].getName();
	    // System.out.println(nameLast);
	      
	     String[] name = nameLast.split("F");
	     String[] name2 = name[1].split("[.]");
	     //System.out.println(name2[0]);
	     int numFichier = Integer.parseInt(name2[0]);
		return numFichier;
	}
	
	/**
	 * fonction permet de récupérer la longueur de fichier
	 * @param file de type File.
	 * @return renvoie la longueur.
	*/
	public long GetLength(File file) throws IOException {
		RandomAccessFile r = new RandomAccessFile(file, "rwd");
		r.writeUTF("Test");
		long length = r.length();
		r.close();
		return length;
	}
	
	/**
	 * fonction permet de lire la pageId.
	 * @param pageID de type PageId.
	 * @param buff de type ByteBuffer
	 */
	public void readPage(PageId pageID, ByteBuffer buffer) {
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
				e.printStackTrace();
			}
		}
    }
	
	/**
	 * fonction permet d'écrire dans  la pageId.
	 * @param pageID de type PageId.
	 * @param buffer de type ByteBuffer
	 */
	public void writePage(PageId pageID, ByteBuffer buffer) {    
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
				e.printStackTrace();
			}

		}
    }
	
	/**
	 * fonction permet de recuperer le nombre de pages allouées.
	 * @return  le nombre.
	 */
	public int GetCurrentCountAllocPages() {
		return pagesAllouees.size();
	}
	
	public static DiskManager getSingleton() {
		return g_instance;
	}
}
