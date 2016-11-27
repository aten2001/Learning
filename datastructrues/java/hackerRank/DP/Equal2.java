
import java.util.*;


public class Equal2 {


    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while(T-- > 0){
            int N = sc.nextInt();
            int[] data = new int[N];
            int min = Integer.MAX_VALUE;
            for(int i=0; i<N; i++){
                data[i] = sc.nextInt();
                if(min > data[i]) min = data[i];
            }
            int minVal = Integer.MAX_VALUE;
            L: for (int k=0; k<5; k++){
                int sum = 0;
                for(int i=0; i<N; i++){
                    int diff = data[i] - min - k;
                    if(diff < 0) continue L;
                    sum += ops(diff);
                }
                if (minVal > sum) minVal = sum;
            }
            System.out.println(minVal);
        }
    }

    private static int ops(int diff){
        return (diff/5) + ((diff%5)/2) + ((diff%5)%2);
    }
}
