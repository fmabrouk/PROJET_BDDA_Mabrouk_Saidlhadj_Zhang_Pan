package up.mi.jgm.TP06;

import up.mi.jgm.TP03.BufferManager;
import up.mi.jgm.TP04.Catalog;

public class DBManager {
	
	private static DBManager dbmanager = new DBManager();
	
	
	public void Init() {
		Catalog.getCatalog().Init();
	}
	
	public void Finish(){
        Catalog.getCatalog().Finish();
        BufferManager.getSingleton().flushAll();;
    }
	
	

	public void ProcessCommand(String Command){
		int Create =Command.indexOf("CREATE TABLE");
		int Insert =Command.indexOf("INSERT");
		int delete =Command.indexOf("DELETE");
		
		if(Create==0) {
			String mots[] = Command.split("CREATE TABLE ");
			CreateTableCommand CreateTable= new CreateTableCommand(mots[1]);
		}
	}
	
	public static DBManager getDBManager() {
		return dbmanager;
	}
}
