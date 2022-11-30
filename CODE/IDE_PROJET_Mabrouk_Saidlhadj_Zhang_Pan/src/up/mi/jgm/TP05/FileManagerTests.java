package up.mi.jgm.TP05;
import java.io.IOException;
import java.nio.ByteBuffer;
import up.mi.jgm.TP03.*;
import up.mi.jgm.TP04.*;
import up.mi.jgm.TP02.*;

public class FileManagerTests {

	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		DBParams.frameCount = 2;
		DBParams.DBPath = "C:\\Users\\fayez\\OneDrive\\Bureau\\BDDA_22\\PROJET_BDDA_Mabrouk_Saidlhadj_Zhang_Pan\\DB";
		//PageId nouvPage = FileManager.getFileManager().createNewHeaderPage();
		PageId nouvPage = FileManager.getFileManager().addDataPage(null);
		
	}
	
    

}
