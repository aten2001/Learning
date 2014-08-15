

public class CC{

  private int[] id;
  private boolean[] marked;
  private int[] size;
  private int count;

  public CC(Graph G){
    this.id = new int[G.V()];
    this.marked = new boolean[G.V()];
    this.size = new int[G.V()];
    this.count = 0;
    for(int i=0; i<G.V(); i++){
      if(this.marked[i]) continue;
      this.id[i]=count;
      dfs(G,i,count);
      count++;
    }
  }

  private void dfs(Graph G, int s, int count){
    this.marked[s]=true;
    this.size[count]++; //count number of connnected components
    for(int w : G.adj(s)){
      if(this.marked[w]) continue;
      this.id[w] = count;
      dfs(G, s, count);
    }
  }

  public boolean connected(int v, int w){
    return this.id[v] == this.id[w];
  }

  public int count(){
    return this.count;
  }

  public int size(int v){
    return this.size[id[v]];
  }

  public int id(int v){
    return this.id[v];
  }

}
