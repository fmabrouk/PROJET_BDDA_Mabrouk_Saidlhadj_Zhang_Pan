package up.mi.jgm.TP03;

import up.mi.jgm.TP02.PageId;
import up.mi.jgm.TP02.DBParams;
import java.nio.ByteBuffer;

public class Frame {
	
	private ByteBuffer buffer;
	private PageId pageId;
	private boolean pinCount;
	private boolean dirty;	
	private boolean	refBit;
	
	public Frame() {
		buffer = null;
		pageId = null;
		pinCount = false;
		dirty = false;
		refBit = false;
	}

	public ByteBuffer getBuffer() {
		return buffer;
	}

	public void setBuffer(ByteBuffer buffer) {
		this.buffer = buffer;
	}

	public PageId getPageId() {
		return pageId;
	}

	public void setPageId(PageId pageId) {
		this.pageId = pageId;
	}

	public boolean getPinCount() {
		return pinCount;
	}

	public void setPinCount(boolean pinCount) {
		this.pinCount = pinCount;
	}

	public boolean getRefBit() {
		return refBit;
	}

	public void setRefBit(boolean refBit) {
		this.refBit = refBit;
	}

	public boolean isDirty() {
		return dirty;
	}

	public void setDirty(boolean dirty) {
		this.dirty = dirty;
	}

	@Override
	public String toString() {
		return "Frame [pageId=" + pageId + ", pinCount=" + pinCount + ", dirty=" + dirty + ", refBit=" + refBit + "]";
	}	
	
	
//	    public PageId id;
//	    public int pin_count;
//	    public boolean dirty;
//	    public byte [] buffer;
//	    public int temps;
//	    
//	    public Frame (PageId id) {
//	    this.id = id;
//	    pin_count = 0;
//	    dirty = false;
//	    buffer = new byte[4096];
//	    temps=0;
//	    }
//	    
//	public String toString() {
//		return "(pageID : ) " +id+ " pin_count : "+pin_count; 
//	}
//	
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
