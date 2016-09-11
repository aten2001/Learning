

public class PrimsMST {

  private Queue<Edge> mst;
  private int wt;
  private boolean[] marked;

  public PrimsMST(EdgeWeightedDirectedGraph G) {
    mst = new Queue<Edge>;
    this.marked = new boolean[G.V()];
    MinPQ<Edge>  pq = new MinPQ<Edge>();
    visit(G,0,pq);
    while( !pq.isEmpty() && mst.size() < G.V()-1 ){
      Edge e = pq.delMin();
      int v = e.either(), w = e.other(v);
      if(marked[v] && marked[w]) continue;
      mst.enque(e);
      if(!marked[v]) visit(G,v,pq);
      if(!marked[w]) visit(G,w,pq);
    }
  }

  private void visit(EdgeWeightedDirectedGraph G, int i, MinPQ pq){
    marked[i]=true;
    for(Edge e : G.adj(i)){
      int w = e.to();
      if(marked[w]) continue;
      pq.insert(e);
    }
  }


}

