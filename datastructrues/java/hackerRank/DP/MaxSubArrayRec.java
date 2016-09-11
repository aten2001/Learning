
/*
 * https://www.hackerrank.com/challenges/maxsubarray
 */
import java.util.*;
import java.lang.*;
import java.math.*;


public class MaxSubArrayRec {


    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0){
            int N = sc.nextInt();
            int[] data  = new int[N];
            int maxVal = Integer.MIN_VALUE;
            int sumNonContiguous = 0;
            for (int i=0; i<N; i++){
                data[i] = sc.nextInt();
                if (maxVal < data[i]) maxVal = data[i];
                if (data[i] > 0) sumNonContiguous += data[i];
            }

            int sum;
            if(maxVal <= 0) {
                sumNonContiguous = maxVal;
                sum = maxVal;
            }else{
                int index=0;
                for(int i=0; i<N; i++){
                    if(data[i] <=0) continue;
                    index = i;
                    break;
                }
                sum = recursiveCheck(data[index],data,index+1);
            }
            System.out.println(sumNonContiguous+" "+sum);
        }
    }


    static int recursiveCheck(int sum, int[] data,int i){
        if (i == data.length) return sum;
        int val = data[i];
        if (val > 0){
            return recursiveCheck((sum+val), data, i+1);
        }else{
            if(val+sum <=0){
                return Math.max(sum, recursiveCheck(0,data,i+1));
            }else{
                return Math.max(sum,recursiveCheck(sum+val,data,i+1));
            }
        }
    }

}
