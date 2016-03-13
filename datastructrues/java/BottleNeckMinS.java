
public class BottleNekMinS {

  private Edge bottleNeckEdge = null;

  public BottleNeckMinS(EdgeWeightedGraph G){

    MinPQ<Edge> pq = new MinPQ<>();
    for(Edge e : G.edges()) pq.add(e);

    UF uf = new UF();
    while(!pq.isEmpty() && mst.size() <= V-1){
      Edge e = pq.delMin();
      int v = e.either(), w = e.other(v);
      if(uf.connected(v,w)) continue;
      else uf.union(v,w);
      mst.push(e);
    }
    bottleNeckEdge = mst.pop();

  }

  public wt bottleNeckWt(){
    return bottleNeckEdge.wt();
  }

}
