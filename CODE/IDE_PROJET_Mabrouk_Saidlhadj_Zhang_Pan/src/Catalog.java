import java.util.ArrayList;

public class Catalog {
    ArrayList<RelationInfo> Data=new ArrayList<RelationInfo>();


  
  
    public void Init(){};
    public void Finish(){};

    public void AddRelationInfo(RelationInfo Ri){
        Data.add(Ri);
    }

    public RelationInfo GetRelationInfo(String nomRelation){
        for(int i=0;i<Data.size();i++){
            if(Data.get(i).getNomrelation().equals(nomRelation)){
                return Data.get(i);
            }
        }
        return null;
    }

}