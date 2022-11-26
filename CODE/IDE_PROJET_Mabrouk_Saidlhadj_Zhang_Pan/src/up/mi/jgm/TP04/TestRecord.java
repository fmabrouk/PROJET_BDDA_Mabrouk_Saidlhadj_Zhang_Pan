package up.mi.jgm.TP04;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;

import up.mi.jgm.TP02.*;

public class TestRecord {
	 public static void main(String[] args) {
		 DBParams.DBPath = "C:\\Users\\fayez\\OneDrive\\Bureau\\BDDA_22\\PROJET_BDDA_Mabrouk_Saidlhadj_Zhang_Pan";
//		 ArrayList<RelationInfo> d = new ArrayList<RelationInfo>();
//		
//		 
//	 	RelationInfo rel = new RelationInfo("Matières");
//	 	d.add(rel);
//	 	
//	 	ColInfo c2 = new ColInfo("Notes "+"REAL");
//	 	ColInfo c = new ColInfo("Nom "+"VARCHAR(15)");
//	 	
//	 	ArrayList<ColInfo> col = new ArrayList<ColInfo>();
//	 	col.add(c2);
//	 	col.add(c);
//	 	rel.setData(col);
//	 	ArrayList<String> valuesRecord =new ArrayList<String>();
//	 	valuesRecord.add("15");
//	 	valuesRecord.add("Maths");
//	 	
//		Catalog cat = new Catalog(d);
//		
//	 	/*
//	 	valuesRecord.add("17");
//	 	valuesRecord.add("Info");
//	 	
//	 	valuesRecord.add("1");
//	 	valuesRecord.add("francais");
//	 	*/
//	 	
//	 	Record rec = new Record(rel, valuesRecord);
//	 	
//	 	ByteBuffer buff =  ByteBuffer.allocate(80);
//	 	
//	 	//rec.afficher();
//	 	
// 	    rec.writeToBuffer(buff,0);
// 	    rec.readFromBuffer(buff, 0);
//	 	System.out.println(rec.getWrittenSize());
     	//buff.putFloat(15.0F);
	 	/*
	 	for(int l=0; l<"Maths".length(); l++) {
    		buff.putChar("Maths".charAt(l));
    	}*/
//	 	int val = Integer.parseInt("15");
//        buff.putInt(val);
	
	 	//buff.putFloat(15.0f);
 	    
 	    //System.out.println(buff.getFloat(0));
 	   
 	    
 	   Catalog.Init();
	 	
 	   // cat.Finish();
 	    
 	    
//	 	System.out.println("Buffer : " +Arrays.toString(buff.array()));
	 	
	 	//System.out.println(Double.SIZE);
		 
		
	}
}