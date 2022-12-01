package up.mi.jgm.TP05;
import java.nio.ByteBuffer;

import up.mi.jgm.TP02.DBParams;
import up.mi.jgm.TP02.PageId; 

public class HeaderPage {
	
	private int mInitPosition = 0;
	private ByteBuffer mByteBuffer = null;

	
	
	public HeaderPage(ByteBuffer buff) {
		mInitPosition = buff.position();
		mByteBuffer = buff;
	}
	
	public void setNombreDataPage() {
		int nbPages = mByteBuffer.getInt(0);
		nbPages++;
		mByteBuffer.putInt(0, nbPages);
	}
	
	public int getNombreDataPage() {
		int nbPages = mByteBuffer.getInt(0);
		return nbPages;
	}
}