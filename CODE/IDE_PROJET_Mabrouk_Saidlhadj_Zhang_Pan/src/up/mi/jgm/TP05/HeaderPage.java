package up.mi.jgm.TP05;
import java.nio.ByteBuffer;

import up.mi.jgm.TP02.DBParams; 

public class HeaderPage {
	
	private int mInitPosition = 0;
	private ByteBuffer mByteBuffer = null;
	
	public HeaderPage(ByteBuffer buff) {
		mInitPosition = buff.position();
		mByteBuffer = buff;
	}
	
	public void setNbEntrySlot(int nb) {
		mByteBuffer.position(DBParams.pageSize - 2*Integer.BYTES);
		mByteBuffer.putInt(nb);
	}

	public int getNbEntrySlot() {
		mByteBuffer.position(DBParams.pageSize - 2*Integer.BYTES);
		return mByteBuffer.getInt();
	}
	/*
	public toString() {
		
		
		return 
	}*/
}
