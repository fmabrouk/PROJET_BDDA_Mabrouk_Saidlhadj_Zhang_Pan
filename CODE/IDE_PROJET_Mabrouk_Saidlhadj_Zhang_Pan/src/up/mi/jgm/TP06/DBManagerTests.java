package up.mi.jgm.TP06;

import java.util.Scanner;

public class DBManagerTests {
	 public static void main(String[] args) {
    DBManager DB = new DBManager();
    Scanner scanner = new Scanner(System.in);
    Boolean fin =false;//savoir quand sortir de do while
    
        System.out.println("Ecrivez votre commande SQL:");
        //String SQL= scanner.nextLine();
        String SQL="CREATE TABLE R   (X:I  NTEGER   ,C2  :REAL,BLA:VARCHAR(10))";
        if(SQL.equals("EXIT")) {
       	 fin=true;
        }else {
       	 DB.ProcessCommand(SQL);
        }
	 }
}
