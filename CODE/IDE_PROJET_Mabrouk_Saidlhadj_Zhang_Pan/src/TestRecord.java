import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;


public class TestRecord {
     public static void main(String[] args) {
         
         RelationInfo rel = new RelationInfo("Matières");
         ColInfo c = new ColInfo("Nom "+"VARCHAR(15)");
         ColInfo c2 = new ColInfo("Notes "+"REAL");
         ArrayList<ColInfo> col = new ArrayList<ColInfo>();
         col.add(c);
         col.add(c2);
         rel.setData(col);
         ArrayList<String> valuesRecord =new ArrayList<String>();
         valuesRecord.add("Maths");
         valuesRecord.add("15");
         /*
         valuesRecord.add("Info");
         valuesRecord.add("17");
         valuesRecord.add("francais");
         valuesRecord.add("1");
         */
         Record rec = new Record(rel, valuesRecord);
         
         ByteBuffer buff =  ByteBuffer.allocate(40);
         
         rec.afficher();
         
         rec.writeToBuffer(buff,0);
         
         System.out.println("Int : "+ buff.getInt());
         
         System.out.println("Buffer : " +Arrays.toString(buff.array()));
         
         //System.out.println(Double.SIZE);
    }
}
