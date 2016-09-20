/*
 * https://www.hackerrank.com/challenges/fair-cut/editorial
 *
 * https://www.hackerrank.com/challenges/unbounded-knapsack
 */
import java.util.*;
import java.math.*;
import java.lang.*;


public class Knapsack {

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while(T-- > 0){
            int N = sc.nextInt();
            int K = sc.nextInt();
            int [] data = new int[N];
            for(int i=0; i<N; i++){
                data[i] = sc.nextInt();
            }
            Arrays.sort(data);
            int output = recurCheck(K,data,N);
            System.out.println(output);
        }
    }


    private static int recurCheck(int sum, int[] data, int maxIndex){
        if (sum == 0) return sum;
        int newIndex = checkDivisibleElseReturnMaxIndex(sum,data,maxIndex); // used to avoid checking terms > sum
        if (newIndex == -1) return sum;                                     // implies some val in data is multiple of sum
        if (newIndex == 0) return 0;                                        // implies all val in data > sum
        int max = 0;                                                        // max of all output
        for(int i=0; i<newIndex; i++){
            int val = data[i] + recurCheck(sum-data[i], data, newIndex);
            if (max < val) max = val;
        }
        return max;
    }

    private static int checkDivisibleElseReturnMaxIndex(int sum, int[] data, int stop){
        int index = stop;
        for(int i=0; i<stop; i++){
            if (data[i] > sum){
                index = i;
                break;
            }

            if (sum % data[i] == 0){
                index = -1;
                break;
            }
        }
        return index;
    }

}
