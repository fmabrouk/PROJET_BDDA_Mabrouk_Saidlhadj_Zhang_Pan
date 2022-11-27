package up.mi.jgm.TP05;
import up.mi.jgm.TP02.*;

public class RecordId {
	public PageId pageId;
	public int slotIdx;
	
	public RecordId() {
		this.pageId=null;
		this.slotIdx=0;
	}
	
	/**
	 *  Obtenir la page à laquelle appartient le record
	 * @return La page à laquelle appartient le record
	 */
	public PageId getPageId() {
		return pageId;
	}

	/**
	 * Ecrire la page à laquelle appartient le record
	 * @param pageId
	 */
	public void setPageId(PageId pageId) {
		this.pageId = pageId;
	}

	/**
	 * Obtenir l'indice de la case où le record est stocké
	 * @return Indice de la case où le record est stocké
	 */
	public int getSlotIdx() {
		return slotIdx;
	}

	/**
	 * Ecrire l'indice de la case où le record est stocké
	 * @param slotIdx
	 */
	public void setSlotIdx(int slotIdx) {
		this.slotIdx = slotIdx;
	}
}
