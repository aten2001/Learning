
public class AcyclicSP {

  private double distTo[];
  private DirectedEdge edgeTo[];

  public AcyclicSP(EdgeWeightedDirectedGraph G , int s){
    this.distTo = new int[G.V()];
    this.edgeTo = (DirectedEdge[]) new Object[G.V()];
    for(int i=0; i<G.V(); i++) this.distTo[i]=Double.INFINITY;
    distTo[s]=0;
    edgeTo[s] = null;

    for( int v : new Toplogical(G).order()){
      relax(G,v);
    }
  }

  private void relax(EdgeWeightedDirectedGraph G, int s){
    for(DirectedEdge e : G.adj(s)){
      int v = e.from(), w = e.to();
      double wt = e.wt();
      if(distTo[w] > distTo[v] + wt){
        distTo[w] = distTo[v]+wt;
        edgeTo[w] = e;
      }
    }
  }

  public boolean hasPathTo(int v){
    return distTo[v] != Double.INFINITY;
  }

  public Iterable<DirectedEdge> pathTo(int v){
    Stack<DirectedEdge> edges = new Stack<DirectedEdge>();
    for(DirectedEdge e : edgeTo[v]; e !=null; e = edgeTo[e.from()]){
      edges.push(e);
    }
    return edges;
  }

  public double distTo(int v){
    return distTo[v];
  }

}
