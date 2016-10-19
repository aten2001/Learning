import java.util.*;
import java.io.*;

public class ShortestReach {

    public static class Bag<T> implements Iterable<T> {

        public static class Node<T> {
            public T val;
            public Node<T> next;
        }

        public Node<T> first;

        public boolean isEmpty(){
            return this.first == null;
        }

        public void add(T obj){
            Node<T> node = new Node<T>();
            node.val = obj;
            node.next = this.first;
            this.first = node;
        }

        public static class BagIt<T> implements Iterator<T>{

            private Node<T> root;
            public BagIt(Node<T> root) {
                this.root = root;
            }
            @Override
                public boolean hasNext() {
                    return root != null;
                }

            @Override
                public T next() {
                    T val = root.val;
                    root = root.next;
                    return val;
                }
        }

        @Override
        public Iterator<T> iterator() {
            return new BagIt<T>(first);
        }

    }


    public static class Graph {

        private List<Edge>[] adj;

        public Graph(int V){
            this.adj = new ArrayList[V];
            for(int i=0; i<V; i++){
                this.adj[i] = new ArrayList<Edge>(V);
            }
        }

        public void addEdge(int v , int w, int wt){
            Edge edge = new Edge(v,w,wt);
            this.adj[v].add(edge);
            this.adj[w].add(edge);
        }

        public Collection<Edge> adj(int v){
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
            long start = System.currentTimeMillis();
            search(G,queue);
            System.out.println(System.currentTimeMillis() - start);
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
                    v = greater(w,v) ? v : w;
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



    public static void main(String[] args) throws IOException {
        try(BufferedReader reader = new  BufferedReader(new InputStreamReader(System.in),32768)){
            String[] line = reader.readLine().split("\\s");
            int T = Integer.parseInt(line[0]);
            while(T-- > 0){
                line = reader.readLine().split("\\s");
                int N = Integer.parseInt(line[0]);
                int M = Integer.parseInt(line[1]);
                Graph G = new Graph(N);
                while(M-- > 0){
                    //line = reader.readLine().split("\\s");
                    //int v = Integer.parseInt(line[0]);
                    //int w = Integer.parseInt(line[1]);
                    //int wt = Integer.parseInt(line[2]);

                    String buff = reader.readLine();
                    int a = buff.indexOf(' ');
                    int b = buff.indexOf(' ',a+1);
                    int v = parseInt(buff.substring(0,a));
                    int w = parseInt(buff.substring(a+1,b));
                    int wt =parseInt(buff.substring(b+1,buff.length()));
                    G.addEdge(v-1,w-1,wt);
                    //G.addEdge(sc.nextInt()-1,sc.nextInt()-1,sc.nextInt());
                }
                line = reader.readLine().split("\\s");
                int source = Integer.parseInt(line[0]);
                //SP sp = new SP(G,source-1);
                //System.out.println(sp.path());
            }
        }
    }

    public static int parseInt( final String s )
    {
        // Check for a sign.
        int num  = 0;
        int sign = -1;
        final int len  = s.length( );
        final char ch  = s.charAt( 0 );
        if ( ch == '-' )
            sign = 1;
        else
            num = '0' - ch;

        // Build the number.
        int i = 1;
        while ( i < len )
            num = num*10 + '0' - s.charAt( i++ );

        return sign * num;
    } 

}
