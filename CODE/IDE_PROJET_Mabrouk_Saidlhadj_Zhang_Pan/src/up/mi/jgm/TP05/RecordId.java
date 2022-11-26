package up.mi.jgm.TP05;
import up.mi.jgm.TP02.*;

public class RecordId {
	public PageId pageid;
	public int slotIdx;
	
	public RecordId(PageId id, int index) {
		pageid=id;
		slotIdx=index;
	}
}
