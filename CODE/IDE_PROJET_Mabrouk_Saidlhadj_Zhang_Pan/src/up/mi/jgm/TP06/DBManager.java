package up.mi.jgm.TP06;

public class DBManager {

	public void ProcessCommand(String Command){
		int Create =Command.indexOf("CREATE TABLE");
		int Insert =Command.indexOf("INSERT");
		int delete =Command.indexOf("DELETE");
		
		if(Create==0) {
			String mots[] = Command.split("CREATE TABLE ");
			CreateTableCommand CreateTable= new CreateTableCommand(mots[1]);
		}
	}
}
