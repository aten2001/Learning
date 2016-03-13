
public class GraphUtils {

  
  public shortestDirectedCycle(DirectedGraph G){
    boolean[] marked = new boolean[G.V()];
    boolean[] cycle = new boolean[G.V()];
    int[] edgeTo = new int[G.V()];
    Queue<Integer> cyclePath = new Queue<Integer> ();
    for(int v : G.V()){
      if(!marked[v]) continue;
      dfs(G,v);
    }
  }

  private void dfs(DirectedGraph G, int v){
    marked[v]=true;
    cycle[v]=true;
    for(int w : G.adj(v)){
      if(!marked[w]){
        edgeTo[w]=v;
        dfs(G,w);
      }
      else{
        if(cycle[w]){
          Queue<Integer> buff = new Queue<Integer>();
          for(int i=v; i!=w; i=edgeTo[i]) buff.enque(i);
          if(cyclePath == null) cyclePath = buff;
          else if(cyclePath.size() > buff.size()) cyclePath = buff;
        }
      }
    }
    cycle[v]=false;
  }



}
