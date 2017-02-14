
public class PrimsEagerMST {
  
  private Queue<Edge> mst;
  private boolean[] marked;
  private int[] distTo;
  private IndexMinPQ<Edge> pq;

  public PrimsEagerMST(EdgeWeightedGraph G){
    this.mst = new Queue<Edge>();
    this.marked = new boolean[G.V()];
    this.distTo = new int[G.V()];
    this.pq = new IndexMinPQ(G.V());

    distTo[0]=0;
    pq.insert(0,0);

    visit(G,0);

    while(!pq.isEmpty()){
      Edge e = pq.delMin();
      mst.enque(e);
      int v = e.either(), int w = e.other(v);
      if(marked[v]) visit(G, w);
      if(marked[w]) visit(G, v);
    }
  }

  private void visit(EdgeWeightGraph G, int w){
    this.marked[w] = true;
    for(Edge e : G.adj(w)){
      int i = e.other(w);
      if(this.marked[i]) continue;
      if(pq.containsKey(i)){
        if(e.compareTo(pq.get(i)) <0) pq.decreaseKey(i,e);
      }else{
        pq.insert(i,e);
      }
    }
  }

}

