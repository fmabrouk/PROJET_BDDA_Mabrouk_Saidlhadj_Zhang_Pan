import java.util.Scanner;

import up.mi.jgm.TP02.*;
import up.mi.jgm.TP06.*;


public class Main {

	public static void main(String[] args) {
		
		
		DBParams.DBPath = "C:\\Users\\fayez\\OneDrive\\Bureau\\BDDA_22\\PROJET_BDDA_Mabrouk_Saidlhadj_Zhang_Pan\\DB";
		DBParams.frameCount = 2;
		DBParams.pageSize = 4096;
        DBParams.maxPagesPerFile = 4;
        
		DBManager.getDBManager().Init();
		Scanner lectureClavier=new Scanner(System.in);
		String chaine;
		while (true){
			System.out.print(">>");
			chaine=lectureClavier.nextLine();
			if (chaine.equals("EXIT")){
				DBManager.getDBManager().Finish();
				break;
			} else if (chaine.equals(""))
				continue;
			DBManager.getDBManager().ProcessCommand(chaine);
		}
		lectureClavier.close();

	}

}
