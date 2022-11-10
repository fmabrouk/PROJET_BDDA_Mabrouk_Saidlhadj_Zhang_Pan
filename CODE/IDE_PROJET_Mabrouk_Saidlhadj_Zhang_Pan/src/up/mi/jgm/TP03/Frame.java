package up.mi.jgm.TP03;

import up.mi.jgm.TP02.PageId;
import up.mi.jgm.TP02.DBParams;
import java.nio.ByteBuffer;

public class Frame {
	public ByteBuffer buffer;
	public PageId pageID;
	//private boolean pin_Count;
	public int pin_Count;
	public boolean flag_dirty;
	//private boolean refBit;
	public ListeChainee chaine;
	
	public Frame(PageId pageID) {
		this.pageID = pageID;
		buffer = ByteBuffer.allocate((int) DBParams.pageSize);
		pin_Count = 0;
		flag_dirty = false;
		//refBit = false;
		chaine = null;
	}
	
	public String toString() {
		return "(pageID : ) " +pageID+ " pin_count : "+pin_Count; 
	}
	
	/*
	public ByteBuffer getBuffer() {
		return buffer;
	}
	
	public void setBuffer(ByteBuffer buffer) {
		this.buffer = buffer;
	}
	
	public PageId getPageId() {
		return pageID;
	}
	
	public void setPageId(PageId pageID) {
		this.pageID = pageID;
	}
	
	public int getPinCount() {
		return pin_Count;
	}
	
	
	public void setPinCount(boolean pin_Count) {
		this.pin_Count = pin_Count;
	}
	
	public boolean getRefBit() {
		return refBit;
	}
	
	public void setRefBit(boolean refBit) {
		this.refBit = refBit;
	}
	
	public boolean getFlagDirty() {
		return flag_dirty;
	}
	
	public void setFlagDirty(boolean flag_dirty) {
		this.flag_dirty = flag_dirty;
	}
	
	@Override
	public String toString() {
		return "Frame [pageId=" + pageID + ", pinCount=" + pin_Count + ", dirty=" + flag_dirty + ", refBit=" + refBit + "]";
	}	
	*/
}
