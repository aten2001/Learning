
/**
 * https://www.hackerrank.com/challenges/red-john-is-back
 *  could be solved by DP similar to coin change
 *  but found is simpler to solve using basic combinactor math
 *  As the cases as simpler
 */


import java.util.*;

public class RedJohnIsBack {

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while(T-- > 0){
            int N = sc.nextInt();
            int noOfVerticalBrickRows = N/4; // no possibilty of horizontal a vetfical together
            int M = 0;
            long[] memo = new long[N+1];
            for (int v=0; v<=noOfVerticalBrickRows; v++){
                int ht = N-(4*v);
                int vt = v;
                int val= getCombinations(memo,ht,vt);
                M +=val;
            }
            int output = noOfPrimes(M);
            System.out.println(output);
        }
    }


    private static int getCombinations(long[] memo, int ht, int vt){
        if(ht == 0 || vt == 0) return 1;
        int sum = ht+vt;
        int max = ht > vt ? ht : vt;
        long num = compute(max+1,sum);
        long den = (max == vt) ? fact(memo,ht) : fact(memo,vt);
        return (int) (num / den);
    }

    private static long fact(long[] memo, int val){
        if (memo[val] != 0) return memo[val];
        long output = compute(2,val);
        memo[val] = output;
        return output;
    }

    private static long compute(int start, int val){
        long output = 1;
        for(int i=start; i<=val; i++){
            output *= i;
        }
        return output;
    }

    private static int noOfPrimes(int M){
        int val = 0;
        boolean[] marked = new boolean[M+1];
        for(int i=2; i<=M; i++){
            if (marked[i]) continue;
            markMultiples(marked,i,M);
            val++;
        }
        return val;
    }

    private static void markMultiples(boolean[] marked, int prime, int limit){
        for(int i=1; i<=(limit/prime); i++){
            marked[prime*i]= true;
        }
    }
}
