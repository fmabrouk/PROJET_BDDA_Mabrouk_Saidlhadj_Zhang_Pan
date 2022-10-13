

public class PageId {
	public int FileIdx;
	public int PageIdx;

	
	    public int getFileIdx() {
	        return FileIdx;
	    }


	    public int getPageIdx() {
	        return PageIdx;
	    }

	   
	   
	    
	    public PageId(int file,int page) {
	        FileIdx = file;
	        PageIdx = page;
	    }
	
	public boolean equals(PageId pi) {
		return (pi.FileIdx==this.FileIdx && pi.PageIdx==this.PageIdx);
	}
	
	@Override
	public String toString() {
		return "PageId [FileIdx = " + FileIdx + ", PageIdx = " + PageIdx + "]";
	}
	

}
