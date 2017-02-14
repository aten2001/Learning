
/*
 * Given a matrix mxn with all entries as 1 or 0. find the size of the 
 * max square sub matrix with all 1s.
 */

import java.util.*;

public class LargestSquareSubMatrix {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        byte[][] data = new byte[N][M];
        int[][] memo = new int[N][M];

        int max = -1;
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                data[i][j] = sc.nextByte();
                if(i == 0 || j == 0){
                    memo[i][j] = data[i][j];
                    if(max < memo[i][j]) max = memo[i][j];
                }
            }
        }

        for(int i=1; i<N; i++){
            for(int j=1; j<M; j++){
                if(data[i][j] == 0) continue;
                int min = Math.min(memo[i][j-1],memo[i-1][j]);
                min = Math.min(min, memo[i-1][j-1]);
                memo[i][j] = min+1;
                if(max < memo[i][j]) max = memo[i][j];
            }
        }

        System.out.println(max);
    }

}
