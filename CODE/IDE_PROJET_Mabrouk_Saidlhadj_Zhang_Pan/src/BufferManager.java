import java.nio.ByteBuffer;

public class BufferManager {
	
	private static BufferManager g_instance1 = new BufferManager();
	private static Frame[] frames = new Frame[DBParams.frameCount];
	
	
	public  ByteBuffer GetPage(PageId pageID) {
		DiskManager disk =  DiskManager.getSingleton();
		ByteBuffer buffer = ByteBuffer.allocate((int) DBParams.pageSize);
		int i;
		boolean chosen = false;

		i = indexOfFrame(pageID);

		if (i != -1) {
			frames[i].setPinCount(true);
			return frames[i].getBuffer();
		}

		for (i = 0; i < frames.length; i++) {
			if (frames[i].getPageId() == null) {
				frames[i].setPageId(pageID);
				disk.readPage(pageID, buffer);
				frames[i].setBuffer(buffer);
				frames[i].setPinCount(true);
				return frames[i].getBuffer();
			}
		}
		
		i=0;

		do

		{
			if (!frames[i].getPinCount() && frames[i].getRefBit())
				frames[i].setRefBit(false);
			else if (!frames[i].getPinCount() && !frames[i].getRefBit())
				chosen = true;
			i = (chosen) ? i : i + 1;
			i = (i == frames.length) ? 0 : i;
		} while (!chosen);

		if (frames[i].getFlagDirty())
			disk.writePage(frames[i].getPageId(), frames[i].getBuffer());
			

		frames[i].setPageId(pageID);
		disk.readPage(pageID, buffer);
		frames[i].setBuffer(buffer);
		frames[i].setPinCount(true);
		frames[i].setRefBit(false);
		frames[i].setFlagDirty(false);
		
		return frames[i].getBuffer();
	}
	
	public int indexOfFrame(PageId pageToFind) {
		int i;
		for (i = 0; i < frames.length; i++)
			if (frames[i].getPageId() != null && frames[i].getPageId().equals(pageToFind))
				return i;

		return -1;
	}
	
	public  void freePage(PageId pageToFree, boolean isDirty) {
		int index = indexOfFrame(pageToFree);

		if (index != -1) {
			if (isDirty)
				frames[index].setFlagDirty(true);
			frames[index].setPinCount(false);
			frames[index].setRefBit(true);
		} else
			System.out.println("The page to free is not in the buffer");
	}
	
	public  void flushAll() {
		DiskManager disk =  DiskManager.getSingleton();
		for (int i = 0; i < frames.length; i++) {
			if (frames[i].getFlagDirty())
				disk.writePage(frames[i].getPageId(), frames[i].getBuffer());
			if (frames[i].getPinCount())
				System.out.println("A page was not liberated");
		}
		
		//bufferManager();
	}
	
	public static BufferManager getSingleton() {
		return g_instance1;
	}

}

