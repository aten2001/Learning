
public class TopologicalSort {

  private boolean[] marked;
  private Stack<Integer> order;

  public TopologicalSort(DirectedGraph G){
    order = new Stack<Integer>();
    for(int i=0; i<G.V(); i++){
      if(this.marked[i]) continue;
      dfs(G,i);
    }
  }

  private void dfs(DirectedGraph G, int s){
    this.marked[s]=true;
    for(int v : G.adj(s)){
      if(this.marked[v])continue;
      dfs(G,v);
    }
    order.push(s);
  }

  public Iterable<Integer> toplogicalOrder(){
    return order;
  }

}
