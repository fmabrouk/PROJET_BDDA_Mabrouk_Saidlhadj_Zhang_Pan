import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Catalog {
	public  ArrayList<RelationInfo> relationTab; 
	public int compteur;
	
	//private static Catalog catalog = new Catalog(); 
	private static Catalog catalog = new Catalog();
	//private static Catalog catalog = Init(); 
	public Catalog() {
		relationTab = new ArrayList<RelationInfo>(0); 
		compteur = 0; 
	}
	
	
	public void Init() {
		try {
            FileInputStream f = new FileInputStream (DBParams.DBPath+File.pathSeparator+"Catalog.sv");
            ObjectInputStream s = new ObjectInputStream(f);
			relationTab = (ArrayList<RelationInfo>) s.readObject(); 
			compteur =  (int) s.readObject();
			s.close();
			f.close();
        }
        catch (IOException e){
			System.out.println(" Erreur E/S ");
			relationTab = new ArrayList<RelationInfo>(0); 
			compteur = 0; //compteur = relationTab.size(); 
		}
        catch (ClassNotFoundException e){
			System.out.println(" Pb classe ");
			relationTab = new ArrayList<RelationInfo>(0); 
			compteur = 0; //compteur = relationTab.size(); 
		}
		
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
		catch (IOException e){System.out.println(" Erreur E/S ");}
		relationTab.clear();
		compteur = 0; 
	}
	
	public void AddRelationInfo(RelationInfo relation) {
		if (!relationTab.contains(relation)){
			relationTab.add(relation);
			compteur += 1; 
		}
	}
	
	public RelationInfo getRelationInfo(String nomRelation) {
		for(RelationInfo e : relationTab) {
			if(e.nomRelation.equals(nomRelation)) {
				return e; 
			}
		}
		return null; 
	}
	
	public static Catalog getSingleton() {
		return catalog;
	}
}
