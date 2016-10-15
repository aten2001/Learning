
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShortestReach {


    public static class Graph {

        private List<Edge>[] adj;

        public Graph(int V){
            this.adj = new ArrayList[V];
            for(int i=0; i<V; i++){
                this.adj[i] = new ArrayList<Edge>();
            }
        }

        public void addEdge(int v , int w, int wt){
            Edge edge = new Edge(v,w,wt);
            this.adj[v].add(edge);
            this.adj[w].add(edge);
        }

        public List<Edge> adj(int v){
            return this.adj[v];
        }

        public int V(){
            return this.adj.length;
        }
    }

    public static class Edge{
        private int v;
        private int w;
        private int wt;

        public Edge(int v, int w, int wt){
            this.v = v;
            this.w = w;
            this.wt = wt;
        }

        public int either(){
            return this.v;
        }

        public int other(int a){
            return  this.v == a ? this.w : this.v;
        }

        public int wt(){
            return this.wt;
        }
    }

    public static class SP {

        private int src;
        private int[] distTo;
        private boolean[] marked;

        public SP(Graph G, int src){
            this.distTo = new int[G.V()];
            this.marked = new boolean[G.V()];
            for(int i=0; i<G.V(); i++){
                this.distTo[i] = -1;
            }
            this.src = src;
            this.distTo[src] = 0;
            IndexMinPQ queue = new IndexMinPQ(G.V());
            queue.add(this.src,0);
            search(G,queue);
        }

        private void search(Graph G, IndexMinPQ queue){

            while(!queue.isEmpty()){
                int v = queue.delMin();
                this.marked[v] = true; //mark when we start exploring it
                for(Edge edg : G.adj(v)){
                    int w = edg.other(v);
                    int wt = edg.wt();
                    if(this.marked[w]) continue; //ignore if marked
                    if(queue.contains(w)){ //after first visit
                        if(distTo[w] > distTo[v]+ wt){ // if this is shorter path
                            distTo[w] = distTo[v] + wt;
                            queue.update(w,distTo[w]);
                        }
                    }else{ //first visit
                        distTo[w] = distTo[v] + wt;
                        queue.add(w,distTo[w]);
                    }
                }
            }
        }

        private String path(){
            StringBuilder builder = new StringBuilder();
            for(int i=0; i<distTo.length; i++){
                if (i == src) continue;
                builder.append(distTo[i]);
                builder.append(" ");
            }
            return builder.toString();
        }

    }

    public static class IndexMinPQ {
        private int[] data; //hold data to external index
        private int[] pq;   //hold internalHeapArrayIndex to externalIndex value
        private int[] qp;   //hold reverse pq externalIndex to arrayindex value
        private int n;      // size

        public IndexMinPQ(int size){
            this.data = new int[size+1];
            this.pq = new int[size+1];
            this.qp = new int[size+1];
            this.n=1;
            for(int i=0; i<pq.length; i++){
                this.pq[i] = -1;
            }
        }

        public boolean isEmpty(){
            return n == 1;
        }

        public boolean contains(int v){
            return pq[v] != -1;
        }

        public void add(int v, int wt){
            this.data[v] = wt;
            this.pq[v] = n;
            this.qp[n] = v;
            swim(n);
            n++;
        }

        public void update(int v, int wt){
            int index = this.pq[v];
            this.data[v] = wt;
            swim(index);
            sink(index);
        }

        public int delMin(){
            n--;
            int val = this.qp[1];
            swap(1,n);
            sink(1);
            return val;
        }


        private void swim(int i){
            if (i > 1){
                if(greater(i/2,i)) {
                    swap(i, i/2);
                    swim(i/2);
                }
            }
        }

        private void sink(int i){
            int v = 2 * i;
            int w = v + 1;
            if (v < n){
                if(w < n){
                    v = greater(v,w) ? v : w;
                }
                if(greater(i,v)){
                    swap(i,v);
                    sink(v);
                }
            }
        }

        private boolean greater(int a, int b){
            return (data[qp[a]] > data[qp[b]]);
        }

        private void swap(int a, int b){
            int buff = qp[a];
            qp[a] = qp[b];
            qp[b] = buff;
            pq[qp[a]] = a;
            pq[qp[b]] = b;
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while(T-- > 0){
            int N = sc.nextInt();
            int M = sc.nextInt();
            Graph G = new Graph(N);
            while(M-- > 0){
                G.addEdge(sc.nextInt()-1,sc.nextInt()-1,sc.nextInt());
            }
            SP sp = new SP(G,sc.nextInt()-1);
            System.out.println(sp.path());
        }
    }

}
