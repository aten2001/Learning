
public class Graph {

  private final int V;
  private int E;
  private Bag<Integer>[] vertices;

  public Graph(int V){
    this.V = V;
    this.vertices = new int[V];
    for(int i=0; i<V; i++) this.vertices[i]=new Bag<Integer>();
  }

  public void addEdge(int a, int b){
    this.vertices[a].add(b);
    this.vertices[b].add(a);
    E++;
  }

  public Iterable<Integer> adj(int v) {
    return this.vertices[v];
  }

  public int V(){
    return V;
  }

  public int E(){
    return this.E;
  }

}
