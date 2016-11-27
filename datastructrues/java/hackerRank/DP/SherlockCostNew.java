
import java.util.*;


public class SherlockCostNew {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while(T-- > 0){
            int N = sc.nextInt();
            int[] data = new int[N];
            int [][] memo = new int[N][2];
            int Seven = 0, Sodd = 0;
            for(int i=0; i<N; i++){
                data[i] = sc.nextInt();
            }
            for(int i=0; i<N-1; i++){
                memo[i+1][0] = Math.max(memo[i][0], memo[i][1]+abs(data[i]-1));
                memo[i+1][1] = Math.max(memo[i][0]+abs(data[i+1]-1), memo[i][1]+abs(data[i]-data[i+1]));
                System.out.println(memo[i][0]+ " : "+memo[i][1]);
            }
        }
    }

    private static int abs(int val){
        if(val < 0) return -1 * val;
        else return val;
    }

}
