
/**
*https://www.hackerrank.com/challenges/hr-city
*/

import java.util.*;


public class HackerRankCity {


    static class HNode{
        public final long sumToOtherNodes;
        public final long miscSum;

        public HNonde(){
            this.miscSum = 0l;
            this.s = 0l;
        }


        public long getModuloOutput(){
            long output = this.miscSum + this.s;
            return output % 10000000007;
        }
    }

    static class Graph{
        /*
         *
         *  0   1
         *  |   |
         *  2 - 3
         *  |   |
         *  4   5
         *
         */

        private final List<HNode>[] adj;
        private final boolean processed[];


        public Graph(HNode node, wt){
            this.adj = new ArrayList[6];
            this.processed = new boolean[6];
            for(int i=0; i<6; i++){
                this.adj[i] = new ArrayList<HNode>();
            }
            this.adj[0].add(2);
            this.adj[1].add(3);
            this.adj[4].add(2);
            this.adj[5].add(3);

            this.adj[2].add(0);
            this.adj[2].add(4);
            this.adj[2].add(3);

            this.adj[3].add(1);
            this.adj[3].add(2);
            this.adj[3].add(5);

            updateDistances();
        }


        private void updateDistances(){

        }

        private  HNode getNode(){
            return this.adj[0];
        }
    }


    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        HNode node = new HNode();
        while(N-- > 0){
            node = new Graph(node, sc.nextInt()).getNode();
        }

        System.out.println(node.getModuloOutput());


    }

}
