
public class GraphMatrix {

  private final int V;
  private int E;
  private boolean [][] vertices;

  public GraphMatrix(int V){
    this.V = V;
    this.E = 0;
    this.vertices = new boolean [V][V];
  }

  public Iterable<Integer> adj(int s){
    Queue<Integer> edgs = new Queue<Integer>();
    for(int i=0; i< vertices[s].length; i++){
      boolean val = vertices[s][i];
      if(val) edgs.enqueue(i);
    }
    return edgs;
  }

  public void addEdge(int a, int b){
    this.vertices[a][b]=true;
    this.vertices[b][a]=true;
  }
