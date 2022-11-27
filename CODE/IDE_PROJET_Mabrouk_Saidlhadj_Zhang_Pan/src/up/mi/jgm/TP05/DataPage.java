package up.mi.jgm.TP05;

import java.nio.ByteBuffer;

import up.mi.jgm.TP02.DBParams;

public class DataPage {
	
	
	private ByteBuffer mByteBuffer = null;
	
	public DataPage(ByteBuffer buff) {
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
}
