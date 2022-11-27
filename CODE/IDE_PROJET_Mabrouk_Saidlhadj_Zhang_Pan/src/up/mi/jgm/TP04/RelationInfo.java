package up.mi.jgm.TP04;
import java.io.Serializable;
import java.util.ArrayList;

import up.mi.jgm.TP02.PageId;

public class RelationInfo implements Serializable{

    private ArrayList<ColInfo> Data=new ArrayList<ColInfo>();
    private String nomRelation;
    private PageId headerPageId;
    
    public RelationInfo(String nomTable,PageId headerPageId) {
    	nomRelation = nomTable;
    	this.headerPageId = headerPageId;
    }

    public void setData(ArrayList<ColInfo> data) {
        Data = data;
    }
     public PageId getHeaderPageId() {
    	 return headerPageId;
     }
    public String getNomrelation() {
    	return nomRelation;
    }

    public ArrayList<ColInfo> getData() {
        return Data;
    }

}