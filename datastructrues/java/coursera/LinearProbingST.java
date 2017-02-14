
public class LinearProbingST {

  private int M = 3001;
  Key[] keys = (Key[]) new Object[M];
  Value[] values = (Value []) new Object[M];

  public void put(Key k, Value val){
    int i=0;
    for(i= hash(key); keys[i] != null; i = (i+1)%M){
      if(keys[i].equals(k)){
       values[i] = val;
       return;
      }
    }
    keys[i] = k;
    values[i]=val;
  }

  public Value get(Key k){
    for(int i=hash(key); keys[i] != null; i= (i+1)%M) {
      if(keys[i].equals(k)) return values[i];
    }
    return null;
  }
