import java.nio.ByteBuffer;

public class Frame {
	private ByteBuffer buffer;
	private PageId pageID;
	private boolean pin_Count;
	private boolean flag_dirty;
	private boolean refBit;
	
	public Frame() {
		buffer = null;
		pageID = null;
		pin_Count = false;
		flag_dirty = false;
		refBit = false;
	}
	
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
	
	public boolean getPinCount() {
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
}
