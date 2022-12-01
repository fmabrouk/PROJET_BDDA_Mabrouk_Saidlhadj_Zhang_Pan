package up.mi.jgm.TP04;

import java.io.Serializable;

public class ColInfo implements Serializable{
	
	private static final long serialVersionUID = 6652459870088798447L;
	
	
    String nom;
    private String type;
    private int taille;
    
    public ColInfo(String s){
    	String nom[] = s.split(" ");
    	
    	this.nom = nom[0];
    	s=nom[1];
        if(s.contains("VARCHAR")){
        String h= "\\(";
        String mots[] = s.split(h);
        h="\\)";
        String fin[] = mots[1].split(h);
        this.type=mots[0];
        this.taille=Integer.parseInt(fin[0]);
        }
        else{
            this.type=s;
            this.taille=0;
        }
    }
   
    public String getNom() {
    	return nom;
    }

    public int getTaille() {
        return taille;
    }

    public String getType() {
        return type;
    }



}