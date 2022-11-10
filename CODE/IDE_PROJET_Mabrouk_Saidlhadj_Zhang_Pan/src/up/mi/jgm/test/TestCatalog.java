package up.mi.jgm.test;

import up.mi.jgm.TP02.DBParams;

public class TestCatalog {
	public static void main(String[] args) {
		DBParams.DBPath = "C:\\Users\\fayez\\OneDrive\\Bureau\\BDDA_22\\PROJET_BDDA_Mabrouk_Saidlhadj_Zhang_Pan\\DB";
		Catalog cat = new Catalog();
		cat.Finish();
		Catalog.Init();
		
		
	}
}
