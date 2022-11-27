package up.mi.jgm.TP06;
import up.mi.jgm.TP04.*;
import java.util.ArrayList;

public class CreateTableCommand implements Command{
	
	private ArrayList<ColInfo> Col;
    private String nomRelation;
    
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
		RelationInfo rel = new RelationInfo(nomRelation);
        
        rel.setData(Col);
   
		Record rec = new Record(rel);

		
	}

}