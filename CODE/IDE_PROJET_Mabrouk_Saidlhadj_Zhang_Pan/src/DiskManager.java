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
        
        if(pagesDesallouees.size()!=0) { // Si on a des pages desallouees, on en recupere une pour allouer une nouvelle page
            PageId p1 = pagesDesallouees.get(0);
            pagesAllouees.add(pagesDesallouees.get(0));// On l'ajoute aux pages alloues
            pagesDesallouees.remove(0); //On la retire des pages desallouees
            return p1;
        }
        
        //S'il n'y a pas de pages desallouees
        PageId ID = new PageId(Npage,taillePage);//On cree une page
        pagesAllouees.add(ID);//On l'ajoute a la liste des pages allouees
        ++taillePage;
        
        if(taillePage>DBParams.maxPagesPerFile ) { // Si on a depasse le nombre de pages maximal dans le fichier
            // On change de fichier
            ++Npage; 
            taillePage=0;
            File dir  = new File(DBParams.DBPath); // On cree un nouveau fichier
            File[] liste = dir.listFiles();// On ajoute le nouveau fichier a la liste des fichiers
            
            int numFichier = GetIndice(liste); // Recupere l'indice du dernier fichier
            numFichier++;
            File fileCreated = new File(DBParams.DBPath+"\\F"+numFichier+".bdda"); // Cree un fichier avec un indice incremente de 1 par rapport au dernier existant
            
            try {
                if(fileCreated.createNewFile()) {
                     System.out.println("Fichier bien cree");
                     PageId ID2 = new PageId(Npage,taillePage); // Si on a bien cree un fichier, on cree la premiere page du fichier
                     return ID2;
                 }
                 else {
                 System.out.println("Fichier pas cree");
                 }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
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
