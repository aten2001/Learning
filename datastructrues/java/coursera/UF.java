import java.util.Map;
import java.util.HashMap;

public class UF {

  private Map<Integer,Integer> data;
  private Map<Integer,Size> sz;

  public UF(){
    data = new HashMap<>();
    sz = new HahsMap<>();
  }

  public boolean connected(int a, int b){
    if (data.containsKey(a) && data.containsKey(b)){
      int da = find(a);
      int db = find(b);
      return da == db;
    }
    return false;
  }

  public void union(int a, int b){
    if(!data.containsKey(a)){
      data.put(a,a);
      sz.put(a,0);
    }
    if(!data.containsKey(b)){
      data.put(b,b);
      sz.put(b,0);
    }
    int da = find(a);
    int db = find(b);
    if(sz.get(da) > sz.get(db)){
      sz.put(da, sz.get(da) + sz.get(db));
      data.put(db,da);
    }else{
      sz.put(db, sz.get(db) + sz.get(db));
      data.put(da,db);
    }
  }

  private int find(int a){
    int v = data.get(a);
    if (v != a){
      data.put(a,data.get(v));
      return find(v);
    }
    return a;
  }

}
