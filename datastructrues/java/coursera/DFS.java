

public class DFS {

  private final boolean marked[];
  private final int edgeTo[];
  private final int s;

  public DFS(Graph G, int s){
    this.s = s;
    this.marked = new boolean[G.V()];
    this.edgeTo = new int[G.V()];
    for(int i=0; i<G.V(); i++) this.edgeTo[i]=i;
    recDfs(G,s);
  }

  private void dfs(Graph G, int s){
    Stack<Integer> stack = new Stack<Integer>();
    stack.push(s);
    while(!stack.isEmpty()){
      int v = stack.pop();
      if(this.marked[v]) continue;
      this.marked[v]=true;
      for(int w : G.adj(v)){
        if(this.marked[w]) continue;
        this.edgeTo[w]=v;
        stack.push(w);
      }
    }
  }

  private void recDfs(Graph G, int s){
    this.marked[s]=true;
    for(int v : G.adj(s)){
      if(this.marked[v]) continue;
      this.edgeTo[v]=s;
      recDfs(G,v);
    }
  }

  public boolean hasPathTo(int v){
    return this.marked[v];
  }

  public Iterable<Integer> pathTo(int v){
    Queue<Integer> queue = new Queue<Integer>();
    int buff = edgeTo[v];
    while(buff != s){
      queue.enque(buff);
    }
    queue.enque(buff);
    return queue;
  }

}
