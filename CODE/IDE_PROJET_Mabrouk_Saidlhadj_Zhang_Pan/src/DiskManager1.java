
public class DiskManager1 {
	private static DiskManager1 g_instance = new DiskManager1();
	
	private DiskManager1(){
		
	}
	
	public static DiskManager1 getSingleton() {
		return g_instance;
	}
	
	
}
