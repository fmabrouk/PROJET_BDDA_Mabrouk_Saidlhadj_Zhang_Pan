package up.mi.jgm.TP05;
import java.nio.ByteBuffer;

import up.mi.jgm.TP02.DBParams; 

public class HeaderPage {
	
	private int mInitPosition = 0;
	private ByteBuffer mByteBuffer = null;
	private int nombreDataPage = 0;
	
	
	public HeaderPage(ByteBuffer buff) {
		mInitPosition = buff.position();
		mByteBuffer = buff;
	}
	
	public void setNombreDataPage() {
		nombreDataPage++;
	}
	
	
	/*
	public toString() {
		
		
		return 
	}*/
}
