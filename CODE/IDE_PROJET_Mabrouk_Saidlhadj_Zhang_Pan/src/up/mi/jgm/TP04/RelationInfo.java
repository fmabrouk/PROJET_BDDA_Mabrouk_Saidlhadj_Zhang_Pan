package up.mi.jgm.TP04;
import java.io.Serializable;
import java.util.ArrayList;

public class RelationInfo implements Serializable{

    private ArrayList<ColInfo> Data=new ArrayList<ColInfo>();
    private String nomRelation;
    
    public RelationInfo(String nomTable) {
    	nomRelation = nomTable;
    }

    public void setData(ArrayList<ColInfo> data) {
        Data = data;
    }

    public String getNomrelation() {
    	return nomRelation;
    }

    public ArrayList<ColInfo> getData() {
        return Data;
    }

}