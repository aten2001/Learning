

public class DirectedCycleDetection{

  private Stack<Integer> cyclePath;
  private boolean[] marked;
  private boolean[] cycle;
  private int[] edgeTo;

  public DirectedCycleDetection(Graph G){
    marked = new boolean[G.V()];
    cycle = new boolean[G.V()];
    edgeTo = new int[G.V()];
    for(int i=0; i<G.V(); i++){
      if(marked[i]) continue;
      dfs(G,i);
    }
  }

  private void dfs(Graph G, int i){
    if(this.cyclePath != null) return;
    this.marked[i]=true;
    this.cycle[i]=true;
    for(int v: G.adj(i)){
      if(this.cycle[v]) {
        this.cyclePath = new Stack<Integer>();
        cyclePath.push(v);
        for(int k=i; k !=v; k=edgeTo[k]){
          cyclePath.push(k);
        }
        cyclePath.push(v);
      }
      else{
        if(this.marked[v]) continue;
        this.edgeTo[v]=i;
        dfs(G,v);
      }
    }
    this.cycle[i]=false;
  }

  public boolean hasDirectedCycle(){
    return this.cyclePath != null;
  }

}
