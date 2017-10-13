import java.util.*;

//http://practice.geeksforgeeks.org/problems/n-queen-problem/0
//
public class NQueenProblem {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while(T-- > 0){
            int N = sc.nextInt();
            List<String> outputs = compute(N);
            if(outputs.isEmpty()) System.out.println(-1);
            else {
                for(String output : outputs){
                    System.out.print("["+output+"]");
                    System.out.print(" ");
                }
                System.out.println();
            }
        }
    }

    static List<String> compute(int N){
        List<String> output = new ArrayList<>();
        InUse check = new InUse(N)
        recCompute(0,output,check,N);
        return output;
    }

    static void recCompute(int j, List<String> output,InUse check, int N){
        for(int i=0; i<N; i++){
            if(check.inUse(i,j)) continue;
            check.mark(i,j);
            if(j == N-1){
                output.add(check.getQueens());
            }else{
                recCompute(j+1,output,check,N);
            }
            check.unMark(i,j);
        }
    }

    static class InUse {

        int N;
        int[] queens;
        boolean[] rowUsed;
        Set<Tupple>[] diagUsed;

        public InUse(int N){
            this.N = N;
            this.queens = new int[N];
            this.rowUsed = new boolean[N];
            this.diagUsed = new HashSet<Tupple>[N]
        }

        public boolean inUse(int i, int j){
            if(rowUsed[i]) return true;
            else {
                if(this.diagUsed != null){
                    for(Set<Tupple> s : this.diagUsed){
                        if(s.contains(new Tupple(i,j))) return true;
                    }
                }
            }
            return false;
        }

        public void mark(int i, int j){
            this.rowUsed[i] = true;
            this.diagUsed[i] = updateDiags(i,j);
            this.queens[j]=i;
        }


        public void unMark(int i, int j){
            this.rowUsed[i] = false;
            this.diagUsed[i] = null;
            this.queens[j]=-1;
        }

        public String getQueens(){
            StirngBuilder builder = new StringBuilder();
            for(int i=0; i<queens.length; i++){
                if(i != 0) builder.append(" ");
                builder.append(queens[i]);
            }
        }
    }


}
