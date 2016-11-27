
import java.util.*;

public class SherlockCostSecond {


    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while(T--> 0){
            int N = sc.nextInt();
            int [] data = new int[N];
            for(int i=0; i<N; i++){
                data[i] = sc.nextInt();
            }

            int sum = recGetSum(data,1,0,0);
            System.out.println(sum);
        }
    }


    private static int recGetSum(int[] data, int i, int sum0, int sum1){
        if (i == data.length) return Math.max(sum0,sum1);

        // case of keeping new val at index i as 1
        int opt0 = sum0;
        int opt1 = sum1 + abs(data[i-1]-1);

        // case of keeping new val at inext i as maxBvalue
        int opt2 = sum0 + abs(data[i] - 1);
        int opt3 = sum1 + abs(data[i] - data[i-1]);

        return recGetSum(data,i+1,Math.max(opt0,opt1), Math.max(opt2, opt3));

    }

    private static int abs(int val){
        if(val < 0) return -1 * val;
        else return val;
    }
}
