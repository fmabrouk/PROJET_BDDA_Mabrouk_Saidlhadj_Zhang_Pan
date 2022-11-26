package up.mi.jgm.TP04;
//import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.ArrayList;
//import java.util.Arrays;



public class Record {
	private int Written;
    private RelationInfo relInfo;
    private ArrayList<String> values =new ArrayList<String>();

    public Record(RelationInfo rel, ArrayList<String> valeurs){
    	values = valeurs;
        this.relInfo = rel;
    }
    
    public int getWrittenSize(){
		return Written;
    	
    }
    /**
     * Ecrire les valeurs du record dans le buffer à partir d'une position donnée
     * @param buff le buffer dans lequel on veut écrire
     * @param pos la position à partir de laquelle on veut écrire
     */
    
    public void writeToBuffer(ByteBuffer buff, int pos){
    		buff.position(pos);
    		writepositions(buff);
    		writeValues(buff);
    		
    	}
    
    	public void writeValues(ByteBuffer buff) {
    		int i=0;

        	for(int x=0; x<values.size()/relInfo.getData().size();x++) {
        		for(int j = 0; j<relInfo.getData().size(); j++) {
        			
        			
        			if(relInfo.getData().get(j).getType().equals("INTEGER")){
        				int val = Integer.parseInt(values.get(i));
                        buff.putInt(val);
                    }
        			
        			
                    if(relInfo.getData().get(j).getType().equals("REAL")){
                    	float number = Float.parseFloat(values.get(i));
                        buff.putFloat(number);
                    
                    }
                    
                    
        			
                    if(relInfo.getData().get(j).getType().equals("VARCHAR")){
                    	for(int l=0; l<values.get(i).length(); l++) {
                    		buff.putChar(values.get(i).charAt(l));
                    	}
                    }
                   
        			i++;
        			
        		}
        	}
    	}
    	
    	/**
    	 * Ecrire les positions auxquelles devront être écrites les valeurs dans le Buffer 
    	 * @param buff le buffer dans lequel on écrit
    	 */
    	public void writepositions(ByteBuffer buff) {
    		int i=0;
    		int positionValues=0;
    		
        	for(int x=0; x<values.size()/relInfo.getData().size();x++) {
        		for(int j = 0; j<relInfo.getData().size(); j++) {
        			
        			
        			if(relInfo.getData().get(j).getType().equals("INTEGER")){
                        buff.putInt(Integer.BYTES);
                        positionValues+=Integer.BYTES;
                    }
        			
                    if(relInfo.getData().get(j).getType().equals("REAL")){
                        buff.putInt(Float.BYTES);
                        positionValues+=Float.BYTES;
                    
                    }
                    if(relInfo.getData().get(j).getType().equals("VARCHAR")){	
                    	buff.putInt(Character.BYTES*values.get(i).length());
                    	positionValues+=Character.BYTES*values.get(i).length();
                    }
                    
        			i++;
        			
        		}
        	}
        	
        	Written=positionValues+values.size()*Integer.BYTES;
    	}
        	 public void readFromBuffer(ByteBuffer buff, int pos) {
      	    	int positionValues=0;
      	    	int j=0;
      	 		System.out.println();
      		 	for(int i=3;i<values.size()*4;i+=4) {
      		 		
      		 		
      		 		if(relInfo.getData().get(j).getType().equals("INTEGER")){
      					
      					
      	                //buff.putInt(val);
      					System.out.println("Int : "+ buff.get(values.size()*Integer.BYTES+positionValues));
      					positionValues+=4;
      	            }
      				
      				
      	            if(relInfo.getData().get(j).getType().equals("REAL")){
      	            	
      	                //buff.putFloat(number);
      	                
      	                System.out.println("Real : "+ buff.getFloat(values.size()*Integer.BYTES+positionValues));
      	                positionValues+=4;
      	            }
      	            
      				
      	            if(relInfo.getData().get(j).getType().equals("VARCHAR")){
      	            	
      	            	/*
      	            	for(int l=0; l<values.get(i).length(); l++) {
      	            		buff.putChar(values.get(i).charAt(l));
      	            	}
      	            	*/
      	            	//positionValues+=values.get(i).length()*2;
      	            	
      	            	
      	            	
      	            	for(int l=0;l<buff.get(i);l+=2) {
      	       	    	System.out.println("Char : "+ buff.getChar(values.size()*4+l+positionValues));
      	            	}
      	            	
      	            	positionValues+=buff.get(i);
      		 		
      	            }
      	            
      	           if(j==relInfo.getData().size()-1) {
      	        	   j=0;
      	           }else {
      	        	 ++j;
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