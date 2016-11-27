
import java.util.*;


public class SherlockCost {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while(T-- > 0){
            int N = sc.nextInt();
            int[] data = new int[N];
            int Seven = 0, Sodd = 0;
            for(int i=0; i<N; i++){
                data[i] = sc.nextInt();
                if(i == 0) continue;
                boolean even = i %2 == 0;
                Seven += even ? abs(data[i] -1) : abs(data[i-1]-1);
                Sodd += !even ? abs(data[i] -1) : abs(data[i-1]-1);
                System.out.println("i: "+i+" Seven: "+Seven+" Sodd: "+Sodd+" data: "+Arrays.toString(data));
            }
            int Sum = Seven < Sodd ? Sodd : Seven;
            System.out.println(Sum);
        }
    }

    private static int abs(int val){
        if(val < 0) return -1 * val;
        else return val;
    }

}
