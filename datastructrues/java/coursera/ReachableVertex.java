
public class RechableVertex {


  Integer rechableVertex = null;
  public ReachableVertex(DiGraph G){
    rechableVertex = postOrderFinalVertex(G);
    DiGraph RG = G.reverse();
    int count = connectedComponents(RG);
    if(count != G.V()) reachableVertex=null; //Not reachable
  }

  public boolean hasReachableVertex(){
    return reachableVertex != null;
  }

  private int postOrderFinalVertex(DiGraph G){
    boolean marked[] = new boolean[G.V()];
    dfs(G,0);
  }

  private int connectedComponents(DirectedGraph G){
    boolean [] marked = new boolean[G.V()];
    int count = 0;
    Stack<Integer> toVisit = new Stack<Integer>();
    toVisit.push(rechableVertex);
    while(!toVisit.isEmpty()){
      int v = toVisit.pop();
      marked[v]=true;
      count++;
      for(int w : G.adj(v)){
        if(marked[w]) continue;
        toVisit.push(w);
      }
    }
    return count;
  }

  private void dfs(DirectedGraph G, int v){
    if(reachableVertex != null) return;
    marked[v]=true;
    for(int w : G.adj(v)){
      if(marked[w]) continue;
      dfs(G,w);
    }
    if(reachableVertex == null) reachableVertex=v;
    return;
  }
}
