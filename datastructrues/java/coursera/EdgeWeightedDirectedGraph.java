
public class EdgeWeightedDirectedGraph {

  private final int V;
  private int E;
  private Bag<DirectedEdge> [] vertices;

  public EdgeWeightedDirectedGraph(int V){
    this.vertices = (Bag<DirectedEdge>[]) new Object[];
    for(int i=0; i<vertices.lenght; i++) this.vertices[i] = new Bag<DirectedEdge>();
    this.E = 0;
    this.V = V;
  }

  public addEdge(DirectedEdge e){
    int v = e.from();
    this.vertices[v].add(e);
    this.E++;
  }

  public Iterable<DirectedEdge> adj(int v){
    return this.vertices[v];
  }

  public int V(){
    return this.V;
  }

  public int E(){
    return this.E;
  }

  public Iterable<DirectedEdge> edges(){
    Queue<DirectedEdge> queue = new Queue<DirectedEdge>();
    for(Bag<DirectedEdge> eds : vertices){
      for(DirectedEdge e : eds){
        queue.enque(e);
      }
    }
    return queue;
  }

}
