

public class QuickUnion {

  private int[] data;
  private int N;

  public QuickUnion(int size){
    this.N = size;
    this.data = new int[size];
    for(int i=0; i<size; i++){
      this.data[i] = i;
    }
  }

  private void root(int p){
    int rootp = data[p];
    if( p == rootp) return rootp;
    else return root(rootp);
  }

  public void union(int p, int q){
    int rootp = root(p);
    int rootq = root(q);
    data[rootp] = rootq;
    N--;
  }

  public boolean connected(int p, int q){
    return root(p) == root(q);
  }

  public int find(int p){
    return data[root(p)];
  }

  public int count(){
    return N;
  }


}

