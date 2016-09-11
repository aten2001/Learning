
import java.util.*;
import java.math.*;
import java.lang.*;

public class MaxSubSeq{


    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        while(T-- > 0){
            String seq1 = sc.next();
            String seq2 = sc.next();
            int[][] memo = new int[seq1.length()][seq2.length()];
            for (int i=0; i<seq1.length(); i++){
                for (int j=0; j<seq2.length(); j++){
                    memo[i][j] = -1;
                }
            }
            int output = maxLen(memo,seq1,seq2,0,0);
            System.out.println(output);
        }
    }

    private static int maxLen(int[][] memo, String seq1, String seq2, int i, int j){
        if (i == seq1.length() || j == seq2.length()) return 0;
        if (memo[i][j] != -1) return memo[i][j];
        if(seq1.charAt(i) == seq2.charAt(j)){
            memo[i][j] = 1 + maxLen(memo,seq1,seq2,i+1,j+1);
        }else{
            memo[i][j] = Math.max(maxLen(memo,seq1,seq2,i+1,j), maxLen(memo,seq1,seq2,i,j+1));
        }
        return memo[i][j];
    }
}
