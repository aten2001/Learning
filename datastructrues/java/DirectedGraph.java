
public class DirectedGraph {

  private final int  V;
  private int E;
  private Bag<Integer>[] vertices;

  public DirectedGraph(int V){
    this.V=V;
    this.E=0;
    this.vertices = new Bag[V];
    for(int i=0; i<V; i++) this.vertices[i] = new Bag();

  }

  public void addEdge(int v, int w){
    this.vertices[v].add(w);
    this.E++;
  }

  public Iterable<Integer> adj(int v){
    return this.vertices[v];
  }

  public int V(){
    return this.V;
  }

  public int E(){
    return this.E;
  }

  public DirectedGraph reverse(){
    DirectedGraph rev = new DirectedGraph(this.V);
    for(int i=0; i<V; i++){
      for(int w : vertices[i]){
        rev.addEdge(w,i);
      }
    }
    return rev;
  }

}

