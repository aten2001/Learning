//https://www.hackerrank.com/contests/rookierank-2/challenges/knightl-on-chessboard
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class KnigthOnChessBoard{

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[][] moves = new int[n][n];
         for(int i=1; i<n; i++){
            for(int j=1; j<n; j++){
                int m;
                if(moves[j][i] != 0) m = moves[j][i];
                else{
                    Knight knight = new Knight(n,i,j);
                    m = knight.moves();
                    moves[i][j] = m;
                }
                System.out.print(m+" ");
            }
            System.out.println();
        }
    }


    static class Knight {

        static class Tupple {
            public int a, b;
            public int moves;
            public Tupple(int a, int b){
                this.a = a;
                this.b = b;
                this.moves=0;
            }

            public Tupple(int a, int b, int moves){
                this.a = a;
                this.b = b;
                this.moves = moves;
            }

            public Collection<Tupple> getAdjTupples(int n, int i, int j){
                int x = a - i, y = b - j;
                List<Tupple> paths = new ArrayList<>();
                if(x > -1 && y > -1){
                    paths.add(new Tupple(x,y,this.moves+1));
                }
                y = b + j;
                if(x > -1 && y < n){
                    paths.add(new Tupple(x,y,this.moves+1));
                }
                x = a + i;
                if(x < n && y < n){
                    paths.add(new Tupple(x,y,this.moves+1));
                }
                y = b - j;
                if(x < n && y > -1){
                    paths.add(new Tupple(x,y,this.moves+1));
                }

                x = a - j;
                y = b - i;
                if(x > -1 && y > -1){
                    paths.add(new Tupple(x,y,this.moves+1));
                }
                y = b + i;
                if(x > -1 && y < n){
                    paths.add(new Tupple(x,y,this.moves+1));
                }
                x = a + j;
                if(x < n && y < n){
                    paths.add(new Tupple(x,y,this.moves+1));
                }
                y = b - i;
                if(x < n && y > -1){
                    paths.add(new Tupple(x,y,this.moves+1));
                }

                return paths;
            }

            public boolean isDestination(int n){
                return (this.a == this.b && this.a == n-1);
            }

            public int getMoves(){
                return this.moves;
            }

            public String toString(){
                return this.a+"_"+this.b+"_"+this.moves;
            }
        }

        int moves = -1;
        int n;
        int i, j;
        boolean [][] marked;
        public Knight(int n, int i, int j){
            this.n = n;
            this.i = i;
            this.j = j;
            this.marked = new boolean[n][n];
            Queue<Tupple> queue = new LinkedList<>();
            queue.add(new Tupple(0,0));
            this.marked[0][0] = true;
            bfs(queue);
        }

        private void bfs(Queue<Tupple> queue){
            do{
                Tupple t = queue.poll();
                //System.out.println(t);
                for(Tupple adj : t.getAdjTupples(n,i,j)){
                    if (adj.isDestination(n)) {
                        moves = adj.getMoves();
                        break;
                    }
                    if (marked[adj.a][adj.b]) continue;
                    marked[adj.a][adj.b] = true;
                    queue.add(adj);
                }
                //System.out.println(queue);
            }while(!queue.isEmpty());
        }

        public int moves(){
            return this.moves;
        }
    }
}
