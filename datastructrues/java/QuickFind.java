

public class QuickFind {

  private int[] node;
  private int N;

  public QuickFind(int size){
    this.node = new int[size];
    for(int i=0; i<size; i++) node[i] = i;
    this.N = size;
  }

  public void union(int p, int q){
    if(connected(p,q)) return;
    int pVal = node[p];
    int qVal = node[q];
    for(int i=0; i<node.length; i++){
      if(node[i] == pVal) node[i] = qVal;
    }
    this.N--;
  }

  public boolean connected(int p, int q){
    return node[p] == node[q];
  }

  public int find(int p){
    return node[p];
  }

  public int count(){
    return N;
  }

}

