
public class KruskalsMST implements MST{

  private final Queue<Edge> mst = null;
  private double wt;
  private boolean[] marked;

  public KruskalsMST(EdgeWeightedGraph G){
    MinPQ<Edge> pq = new MinPQ<Edge>();
    for(Edge ed : G.edges()) pq.insert(ed);

    while(!pq.isEmpty() && mst.size() < G.V()-1){
      Edge e = pq.delMin();
      int v = e.either(), w = e.other();
      if(marked[v] && marked[w]) continue; //Does the path create a cycle
      this.wt+=e.wt;
      marked[v]=true;
      marked[w]=true;
      mst.enque(e);
    }
  }

  public Iterable<Edge> mst(){
    return mst;
  }

  public double weight(){
    return wt;
  }

}
