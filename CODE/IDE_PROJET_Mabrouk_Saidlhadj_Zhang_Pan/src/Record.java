import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class Record {
	 RelationInfo relInfo;
	 public Object [] values; 
	 
	 private static Record record = new Record(null);
	 public Record(RelationInfo rel) { 
			relInfo = rel; 
			values = new Object [relInfo.nbColonnes]; 
	 }
	 
	 public void writeToBuffer(ByteBuffer buffer, int position) {
			buffer.position(position); 
			for(int i = 0; i < relInfo.nbColonnes; i++) {
				Object tmp= values[i]; 
				if(tmp instanceof Integer) {
					buffer.putInt(((Integer) tmp).intValue());
					}
				else if(tmp instanceof Float) {
					buffer.putFloat(((Float) tmp).floatValue()); 
				}
				else {
					buffer.put(((String) tmp).getBytes(StandardCharsets.UTF_16)); 
				}
			}
		}
	 
	 public void readFromBuffer(ByteBuffer buffer, int position) {
			buffer.position(position); 
			for(int i = 0; i< relInfo.nbColonnes; i++) {
				if (relInfo.infoCol.get(i).typeCol.trim().toLowerCase().equals("int")) {
					values[i] =buffer.getInt();
				}
				else if (relInfo.infoCol.get(i).typeCol.trim().toLowerCase().equals("float")) {
					values[i] = buffer.getFloat();
				}
				else { 
					int length = Integer.valueOf(relInfo.infoCol.get(i).typeCol.substring(6))*Character.BYTES +2; 
					byte [] tab = new byte[length]; 
					buffer.get(tab, 0, length);
					values[i]= new String(tab, StandardCharsets.UTF_16); 
				}
			}
			
		}
	 
	 public static Record getSingleton() {
			return record;
		}
	 
}
