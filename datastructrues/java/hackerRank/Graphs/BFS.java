
import java.util.*;


public class BFS {

    static class Graph {
        private List<Integer> adj[] ;

        public Graph(int V){
            this.adj = new List[V];
            for(int i=0; i<V; i++){
                this.adj[i] = new ArrayList<Integer>();
            }
        }

        public List<Integer> adj(int v){
            return this.adj[v];
        }

        public void addEdge(int v, int w){
            this.adj[v].add(w);
            this.adj[w].add(v);
        }

        public int V(){
            return this.adj.length;
        }
    }


    static class SP {

        private int[] hops;
        private boolean[] marked;

        public SP(Graph G, int src){
            this.hops = new int[G.V()];
            this.marked = new boolean[G.V()];
            for(int i=0; i<G.V(); i++){
                this.hops[i] = -1;
            }
            this.hops[src] = 0;
            Queue<Integer> queue = new LinkedList<Integer>();
            queue.add(src);
            this.marked[src] = true;
            while(!queue.isEmpty()){
                int val = queue.poll();
                for(int w : G.adj(val)){
                    if (this.marked[w]) continue;
                    this.hops[w] = this.hops[val]+1;
                    this.marked[w] = true;
                    queue.add(w);
                }
            }
        }

        private String path(){
            StringBuilder builder = new StringBuilder();
            for(int i=0; i<this.hops.length; i++){
                if(this.hops[i] == 0) continue; // ignore source
                int val = (this.hops[i] == -1) ? -1 : this.hops[i]*6;
                builder.append(val+" ");
            }
            return builder.toString();
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int q = sc.nextInt();
        while(q-- > 0){
            int V = sc.nextInt();
            int E = sc.nextInt();
            Graph G = new Graph(V);
            while(E-- > 0){
                G.addEdge(sc.nextInt()-1, sc.nextInt()-1);
            }
            SP sp = new SP(G, sc.nextInt()-1);
            System.out.println(sp.path());
        }
    }

}
