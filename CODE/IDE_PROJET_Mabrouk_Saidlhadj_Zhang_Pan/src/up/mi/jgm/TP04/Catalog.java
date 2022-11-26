package up.mi.jgm.TP04;
import up.mi.jgm.TP02.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Catalog implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 6652459870088798447L;
	
	ArrayList<RelationInfo> Data;
    
    public  ArrayList<RelationInfo> relationTab; 
    public int compteur; 
    
    /*
    public Catalog() {
        relationTab = new ArrayList<RelationInfo>(); 
        compteur = 0; 
    }
    */
    
    public ArrayList<RelationInfo> getRelationTab() {
		return relationTab;
	}

	public void setRelationTab(ArrayList<RelationInfo> relationTab) {
		this.relationTab = relationTab;
	}

	public Catalog(ArrayList<RelationInfo> d) {
    	relationTab  =new ArrayList<RelationInfo>();
    	relationTab  = d;
    }

  
    public static void Init() {
        try {
            FileInputStream f = new FileInputStream (DBParams.DBPath+File.separator+"Catalog.sv");
            ObjectInputStream s = new ObjectInputStream(f);
           //relationTab =((Catalog) s).readObject().relationTab; 
            //compteur = (Catalog) s.readObject().compteur;
            Catalog C = (Catalog) s.readObject(); 
            C.test();
            s.close();
            f.close();
        }catch (FileNotFoundException e){
            //return new Catalog(); 
        }
        
        catch (IOException e){
            System.out.println(" Erreur E/S ");
            e.printStackTrace();
            //return new Catalog(); 
        }
        catch (ClassNotFoundException e){
            System.out.println(" Pb classe ");
            //return new Catalog(); 
        }
        
        //return catalog;
        
        
    }
    
    public void Finish() {
        try {
            FileOutputStream f = new FileOutputStream (DBParams.DBPath+File.separator+"Catalog.sv");
            ObjectOutputStream s = new ObjectOutputStream(f);
            s.writeObject(this);
            s.flush();
            s.close();
            f.close();
        }
        catch (IOException e){
            System.out.println(" Erreur E/S FINISH CAT ");
            e.printStackTrace();
        }
        relationTab.clear();
        compteur = 0; 
    }
    

    
    /**
     * Récupérer une RelationInfo dans la liste
     * @param nomRelation le nom de la relation que l'on cherche
     * @return la relationInfo que l'on a cherché à partir de son nom
     */
    public RelationInfo GetRelationInfo(String nomRelation){
        for(int i=0;i<Data.size();i++){
            if(Data.get(i).getNomrelation().equals(nomRelation)){
                return Data.get(i);
            }
        }
        return null;
    }
    
    public void test(){
        
        System.out.println(this.getRelationTab().get(0).getData().get(0).getNom());
    }

}