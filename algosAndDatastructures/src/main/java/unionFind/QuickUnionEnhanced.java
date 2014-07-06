package unionFind;
/**
 * Weighted Quick union with path compression implemenation of UF.java
 **/


public class QuickUnionEnhanced implements UF{

  private int[] data = null;
  private int[] size = null; // size added to ensure length does not go over LgN

  public QuickUnionEnhanced(int N){
    this.data = new int[N];
    this.size = new int[N];
    for(int i=0; i<N; i++){
      this.data[i] = i;
      this.size[i] = 1;
    }
  }

  public void union (int first, int next){
    int rFirst = root(first);
    int rNext = root(next);
    if(rFirst == rNext) return;
    int fSize = this.size[rFirst];
    int sSize = this.size[rNext];
    if(fSize < sSize){
    	this.data[rFirst] = rNext;
    	this.size[rNext] = fSize + sSize;
    }
    else{
    	this.data[rNext] = rFirst;
    	this.size[rFirst] = fSize + sSize;
    }
  }

  public boolean connected(int first, int next){
    return root(first) == root(next);
  }


  private int root(int val){
    int v = data[val];
    while(v != val){
      data[val] = data[v]; // path compression fix
      val = v;
      v = data[val];
    }
    return v;
  }

}
