import java.nio.ByteBuffer;

public class CatalogTests {

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		
		DBParams.DBPath = "C:\\Users\\fayez\\OneDrive\\Bureau\\BDDA_22\\PROJET_BDDA_Mabrouk_Saidlhadj_Zhang_Pan\\DB";
		DBParams.pageSize = 4096;
		DBParams.maxPagesPerFile = 4;
		DBParams.frameCount = 2;
		Catalog catalog =  Catalog.getSingleton();
		Record record =  Record.getSingleton();
		ByteBuffer br1 = ByteBuffer.allocate(DBParams.pageSize);
		record.writeToBuffer(br1, 0);
		record.readFromBuffer(br1, 0);
		//catalog.Init();
		catalog.Finish();
		
		

	}

}
