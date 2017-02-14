

public class UF {

  public int [] ids;
  public int [] sz;

  public UF(int N){
    this.ids = new int[N];
    this.sz = new int[N];
    for(int i=0; i<N; i++){
      this.ids[i] = i;
      this.sz[i] = 1;
    }
  }

  public void union (int a, int b){
    int v = root(a);
    int w = root(b);
    if(v == w) return;
    if(this.sz[v] > this.sz[w]){
      this.ids[w] = v;
      this.sz[v] += this.sz[w];
    }else{
      this.ids[v] = w;
      this.sz[w] += this.sz[v];
    }
  }


  public boolean connected(int a, int b){
    return root(a) == root(b);
  }

  public int root(int v){
    if(v == ids[v]) return v;
    else{
      ids[v] = ids[ids[v]]; // path compression
      return root(ids[v]);
    }
  }

}
