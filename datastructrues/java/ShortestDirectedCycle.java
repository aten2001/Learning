

public class ShortestDirectedCycle {

  private boolean[] marked;
  private Stack<Integer> cycle;

  public ShortestDirectedCycle(DiGraph G){
    for(int i=0; i< G.V(); i++){
      this.marked[] = new boolean[G.V()];
      Queue<Integer> toVisit = new Queue<Integer>();
      toVisit.enque(i);
      marked[i]=true;
      bfs(G,toVisit);
    }
  }

  private void bfs(DirectedGraph G, Queue<Integer> toVisit){
    Stack<Integer> buffCycle = null;
    while(!toVisit.isEmpty()){
      int s = toVisit.deque();
      for(int w : G.adj(s)){
        if(buffCycle != null) return;
        if(!marked[w]){
          marked[w]=true;
          edgeTo[w]=s;
          toVisit(w);
        }
        else{
          buffCycle = new Stack<Integer>();
          buffCycle.push(w);
          for(int i=s; i !=w ; i = edgeTo[i]){
            buffCycle.push(i);
          }
          buffCycle.push(w);
          if(cycle == null) cycle = buffCycle;
          else if(cycle.size() > buffCycle.size()) cycle = buffCycle;
        }
      }
    }
  }

}


