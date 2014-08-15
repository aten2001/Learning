
public class EdgeWeightedDiGraph {

  private int V;
  private int E;
  private Bag<Edge>[] vertices;

  public EdgeWeigthedDiGraph(int V){
    this.E=0;
    this.V=V;
    this.vertices = new int[V];
    for(int i=0; i<V; i++) this.vertices[i] = new Bag<Edge>();
  }

  public void addEdge(Edge ed){
    int v = ed.either();
    int w = ed.other(v);
    this.vertices[v].add(w);
    this.vertices[w].add(v);
    this.E++;
  }

  public Iterable<Edge> adj(int v){
    return this.vertices[v];
  }

  public Iterable<Edge> edges(){
    Queue<Edge> output = new Queue<Edge>();
    for(Bag<Edge> eds : this.vertices){
      for(Edge e : eds){
        output.enque(e);
      }
    }
    return output;
  }

  public int V(){
    return this.V;
  }

  public int E(){
    return this.E;
  }

}
