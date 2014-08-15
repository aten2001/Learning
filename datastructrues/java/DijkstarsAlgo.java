
public class DijkstarsAlgo {

  private DirectedEdge[] edgeTo;
  private double distTo[];

  public DijkstarsAlgo(EdgeWeightedDirected G, int s){
    this.edgeTo = (DirectedEdge[]) new Object[G.V()];
    this.distTo = new double[G.V()];
    for(int i=0; i<G.V(); i++) distTo[i] = Double.INFINITY;
    distTo[s]=0;
    edgeTo[s]= null;
    IndexMinPQ<Double> pq = new IndexMinPQ<Double>(G.V());
    pq.insert(s,0.0);
    relax(pq,s);
    while(!pq.isEmpty()){
      int key = pq.minIndex();
      double wt = pq.get(key);
      pq.delete(key);
      relax(pq,key);
    }
  }


  private void relax(IndexMinPQ pq, int s){
    for(DirectedEdge e : G.adj(s)){
      int w = e.to(), v = e.from();
      double wt = e.wt();
      if(distTo[w] > distTo[v] + wt){
        distTo[w] = distTo[v] + wt;
        edgeTo[w] = e;

        if(pq.contains(w)) pq.decreaseKey(w,distTo[w]);
        else pq.insert(w,distTo[w]);
      }
    }
  }

  public boolean hasPathTo(int v){
    return distTo[v] != Double.INFINITY;
  }

  public Iterable<DirectedEdge> pathTo(int v){
    Stack<DirectedEdge> edges = new Stack<DirectedEdge>();
    for(DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from]){
      edges.push(e);
    }
    return edges;
  }

  public double distTo(int v){
    return distTo[v];
  }

}

