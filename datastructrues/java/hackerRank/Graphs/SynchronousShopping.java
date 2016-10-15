
import java.util.*;

public class SynchronousShopping {

    static class Node {
        public int id;
        public short fishBmap;

        public Node(int id, short bitMap){
            this.id = id;
            this.fishBmap = bitMap;
        }
    }

    static class Edge {
        public int v;
        public int w;
        public int wt;

        public Edge(int v, int w, int wt){
            this.v = v;
            this.w = w;
            this.wt = wt;
        }
    }


    static class Graph {

        private final Node[]nodes;
        private final List<Edge>[] adj;

        public Graph(Node[] nodes){
            this.nodes = nodes;
            this.adj = new List[nodes.length];
            for(int i=0; i<nodes.length; i++){
                this.adj[i] = new ArrayList<Edge>();
            }
        }

        public void addEdge(Edge edge){
            this.adj[edge.v].add(edge);
            this.adj[edge.w].add(edge);
        }

        public List<Edge> adj(int v){
            return this.adj[v];
        }

        public int size(){
            return this.nodes.length;
        }
    }


    public static class Djkstar {

        private int edgeTo[];
        private int dist[];
        private int marked[];

        public Djksar(int src, Graph G) {
            this.edgeTo = new int[G.size()];
            this.dist = new int[G.size()];
            for(int i=0; i<G.size(): i++) dist[i] = Integer.MAX_VALUE;
            this.dist[src] = 0;
            this.edgeTo[src] = src;
            TreeMap<Integer,Integer> queue = new TreeMap();
            queue.put(src,
            findPath(queue,G)
        }

        public void findPath(MinPQ queue, Graph G){
            while(queue.size() > 0){
                int v = queue.delMin();
                marked[v] = true;
                for(Edge edg : G.adj(v)){
                    int w = edg.other(v);
                    if marked[w] = continue;
                    Node n = G.getNode(w);
                    if(dist[w] > dist[v] + edg.wt){
                        dist[w] = dist[v]+ edg.wt;
                        edgeTo[w] = v;
                        queue.add(w)
                    }
                }
            }
        }


    }


    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();

        Node[] nodes = new Node[n];
        for(int j =0; j< n; j++){
            int v = sc.nextInt();
            short btmap = 0;
            while (v--  > 0){
                int i = sc.nextInt();
                btmap |= 1 << i;
            }
            nodes[j] = new Node(j,btmap);
        }

        Graph G = new Graph(nodes);
        for(int j =0; j<m; j++){
            int v = sc.nextInt() - 1;
            int w = sc.nextInt() - 1;
            int wt = sc.nextInt();
            Edge edge = new Edge(v,w,wt);
            G.addEdge(edge);
        }


    }

}
