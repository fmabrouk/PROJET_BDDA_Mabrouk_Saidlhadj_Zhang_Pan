package up.mi.jgm.TP06;
import up.mi.jgm.TP04.*;
import up.mi.jgm.TP05.*;
import up.mi.jgm.TP02.*;
import java.util.ArrayList;

public class CreateTableCommand implements Command{
	
	public String nomRelation; 
	public int nbColonnes; 
	public ArrayList<String> nomColonne;
	public ArrayList<String> typeColonne; 
    
    
    
    public CreateTableCommand(String SQL) {
    	
    	SQL=SQL.substring(0,SQL.length()-1);
    	String[] nom = SQL.split(" \\(");
    	
    	nom[0]=nom[0].trim();
    	
    	nomRelation=nom[0];
    	
    	//System.out.println(nom[0].trim());//recupere le nom
    	//System.out.println(nom[1].replaceAll("\\s", ""));//enlever les espaces entre les : et les noms
    	
    	nom[1]=nom[1].replaceAll("\\s", "");
    	nom[1]=nom[1].replaceAll(":", " ");
    	
    	
    	String[] variables = nom[1].split(",");
    	
    	
    	Col = new ArrayList<ColInfo>();
    	
    	for(int i=0;i<variables.length;i++) {
    		ColInfo c = new ColInfo(variables[i]);
    		Col.add(c);
    	}  
        
    	
        Execute();
    }
    
	@Override
	public void Execute() {
		PageId pageId= FileManager.getFileManager().createNewHeaderPage();
		ArrayList<ColInfo> tab= new ArrayList<ColInfo>();
		
		for(int i=0; i<nomColonne.size(); i++) {
			ColInfo colinf= new ColInfo(nomColonne.get(i));
			tab.add(colinf);
		}
		nbColonnes=tab.size();
		//RelationInfo rel =new RelationInfo(nomRelation, pageId);
		

		
	}

}