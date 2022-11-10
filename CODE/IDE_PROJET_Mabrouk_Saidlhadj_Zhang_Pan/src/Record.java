import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Record {
	RelationInfo relInfo;
    ArrayList<String> values =new ArrayList<String>();

    public Record(RelationInfo rel, ArrayList<String> valeurs){
    	values = valeurs;
        this.relInfo = rel;
    }
    
    public void writeToBuffer(ByteBuffer buff, int pos){
    	
    	int i=0;
    	int position=pos;
    	
    	for(int x=0; x<values.size()/relInfo.getData().size();x++) {
    		for(int j = 0; j<relInfo.getData().size(); j++) {
    			
    			
    			if(relInfo.getData().get(j).getType().equals("INTEGER")){
                    buff.putInt(position,4);
                    position+=4;
                    System.out.println(position);
                }
                if(relInfo.getData().get(j).getType().equals("REAL")){
                    buff.putInt(position,4);
                    position+=4;
                    System.out.println(position);
                }
                if(relInfo.getData().get(j).getType().equals("VARCHAR")){	
                	buff.putInt(position,2*values.get(i).length());
                	position+=2*values.get(i).length();
                	System.out.println(position);
                }
                
    			i++;
    			
    		}
    	}
    	
    	/*
        for(int i=0;i<relInfo.getData().size();i++){
        	
	        relInfo.getData().get(i).getType();
	        
	            if(relInfo.getData().get(i).getType()=="INTEGER"){
	                int y=Integer.parseInt(values.get(i));
	                buff.putInt(pos,y);
	            }
	            if(relInfo.getData().get(i).getType()=="REAL"){
	            	
	                float y=Float.parseFloat(values.get(i));
	                buff.putFloat(pos,y);
	            }
	            if(relInfo.getData().get(i).getType()=="VARCHAR"){
	            	
	                for(int x=0;x<values.get(i).length();x++){
	                    //pas terminer
	                	
	                    char charVar = values.get(i).charAt(x);
	                    buff.putChar(pos,charVar);
	                }
	            }

        }
        */
    }
    
    public void afficher() {
    	int i=0;
    	for(int x=0; x<values.size()/relInfo.getData().size();x++) {
    		for(int j = 0; j<relInfo.getData().size(); j++) {
    			System.out.print(relInfo.getData().get(j).getNom() + ": ");
    			System.out.println(values.get(i));
    			i++;
    			
    		}
    	}
    }
}
