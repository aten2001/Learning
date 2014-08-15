
public class BFS {

  private boolean[] marked;
  private int[] edgeTo;
  private int[] distTo;
  private int s;

  public BFS(Graph G, int s){
    init(G,s);
    Queue<Integer> q = new Queue<Integer>();
    q.enque(s);
    this.marked[s]=true;
    bfs(G,q);
  }

  public BFS(Graph G, List<Integer> sources){
    init(G);
    Queue<Integer> q = new Queue<Integer>();
    for(int s : sources){
     q.enque(s);
     this.marked[s]=true;
    }
    bfs(G,q);
  }

  private void init(Graph G, int s){
    this.marked = new boolean[G.V()];
    this.edgeTo = new int[G.V()];
    this.distTo = new int[G.V()];
    for(int i=0; i<G.V(); i++){
      this.edgeTo[i] = i;
      this.distTo[i] = 0;
    }
    this.s=s;
  }

  private void bfs(Graph G, Queue<Integer> q){
    while (!q.isEmpty()){
      int w = q.deque();
      for(int v: G.adj(w)){
        if(this.marked[v]) continue;
        q.enque(v);
        this.marked[v]=true;
        this.edgeTo[v]=w;
        this.distTo[v] = this.distTo[w] + 1;
      }
    }
  }

  public boolean hasPathTo(int v){
    this.marked[v];
  }

  public int distTo(int v){
    return this.distTo[v];
  }

  public Iterable<Integer> pathTo(int v){
    Queue<Integer> output = new Queue<Integer>();
    for(int i=v; i!=this.s; i=this.edgeTo[i]){
      output.enqueue(i);
    }
    output.enqueue(s);
    return output;
  }

}
