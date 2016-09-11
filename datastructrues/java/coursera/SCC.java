

public class SCC {

  private int[] id;
  private int[] size;
  private boolean[] marked;
  private int count;

  public SCC(DirectedGraph G){
    this.id = new int[G.V()];
    this.size = new int[G.V()];
    this.count = 0;
    TopologicalSort top = new TopologicalSort(G.reverse());
    for(int v : top.topologicalOrder()){
      if(marked[v]) continue;
      dfs(G,v,count++);
    }
  }

  private void dfs(Graph G, int s, int count){
    this.marked[s]=true;
    this.id[s]=count;
    this.size[count]++;
    for(int w : G.adj(s)){
      if(this.marked[w]) continue;
      dfs(G,w,count);
    }
  }

  public boolean isConnected(int a, int b){
    return id[a] == id[b];
  }

  public int componentId(int a){
    return id[a];
  }


  public int count(int a){
    return size[id[a]];
  }
