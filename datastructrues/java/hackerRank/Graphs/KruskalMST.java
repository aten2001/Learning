import java.util.*;

public class KruskalMST {

  static class Edge implements Comparable<Edge>{
    public int v;
    public int w;
    public int wt;

    public Edge(int v, int w, int wt){
      this.v = v;
      this.w = w;
      this.wt = wt;
    }

    @Override
    public int compareTo(Edge other) {
      if(this.wt == other.wt){
        return this.sum() - other.sum();
      }
      return this.wt - other.wt;
    }

    private int sum(){
      return this.v + this.wt + this.w;
    }
  }

  static class UF {

    private int [] data;
    private int CC;
    private int [] sz;

    public UF(int N){
      this.data = new int[N];
      this.sz = new int[N];
      this.CC = N;
      for(int i=0; i<N; i++){
        this.data[i] = i;
        this.sz[i] = 1;
      }
      //System.out.println(Arrays.toString(data));
    }

    public void join(int v, int w){
      int a = root(v);
      int b = root(w);
      if(a == b) return;
      CC--;
      if(sz[a] > sz[b]){
        data[b] = a;
        sz[a] += sz[b];
      }else{
        data[a] = b;
        sz[b] += sz[a];
      }
    }

    private int root(int v){
      while(v != data[v]){
        data[v] = data[data[v]];
        v = data[v];
      }
      return v;
    }

    public boolean connected(int v, int w){
      //System.out.println(Arrays.toString(data));
      return root(v) == root(w);
    }

    public boolean allConnected(){
      return this.CC == 1;
    }
  }

  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    int M = sc.nextInt();
    Edge[] edges = new Edge[M];
    UF uf = new UF(N);
    for(int i=0; i<M; i++){
      edges[i] = new Edge(sc.nextInt(),
          sc.nextInt(), sc.nextInt());
    }

    Arrays.sort(edges);

    int mstWt = 0;
    for(int i=0; i<M; i++){
      Edge ed = edges[i];
      int v = ed.v;
      int w = ed.w;
      if(uf.connected(v-1,w-1)) continue;
      mstWt += ed.wt;
      uf.join(v-1,w-1);
      if(uf.allConnected()) break;
    }
    System.out.println(mstWt);
  }
}
