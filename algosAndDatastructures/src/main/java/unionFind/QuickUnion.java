package unionFind;

/**
 * Quick union implemenation of UF.java
 **/


public class QuickUnion implements UF{

  private int[] data = null;

  public QuickUnion(int N){
    this.data = new int[N];
    for(int i=0; i<N; i++){
      this.data[i] = i;
    }
  }

  public void union (int first, int next){
    int rFirst = root(first);
    int rNext = root(next);
    if(first == next) return;
    this.data[rFirst] = rNext;
  }

  public boolean connected(int first, int next){
    return root(first) == root(next);
  }

  private int root(int val){
    int v = data[val];
    while(v != val){
      val = v;
      v = data[val];
    }
    return v;
  }

}
