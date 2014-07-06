
public class WeightedQuickUnion {

  private int[] data, size;
  private int N;

  public WeightedQuickUnion(int sz){
    this.N = sz;
    this.data = new int[sz];
    this.size = new int[sz];
    for(int i=0; i<sz; i++){
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
    int psz = size[p], qsz= size[q];
    if(psz > qsz){
      data[rootq] = rootp;
      size[p] = psz + qsz;
    }else{
      data[rootp] = rootq;
      size[q] = psz + qsz;
    }
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

