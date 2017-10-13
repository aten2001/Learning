import java.util.*;

public class CollectBalls3 {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while(T-- >0){
            int N = sc.nextInt();
            int M = sc.nextInt();
            int[] a = new int[N];
            int[] b = new int[M];
            for(int i=0; i<N; i++) a[i] = sc.nextInt();
            for(int i=0; i<M; i++) b[i] = sc.nextInt();

            Arrays.sort(a);
            Arrays.sort(b);

            long asum = 0L, bsum =0L, sum = 0L;
            int i = 0, j=0;
            while(i<N || j<M){

                if(i >= N) bsum += b[j++];
                else if(j >= M) asum += a[i++];
                else { //i<N && j<M
                    if(a[i] == b[j]){
                        asum += a[i];
                        bsum += b[j];
                        if(asum > bsum) sum += asum;
                        else sum += bsum;
                        asum = 0L;
                        bsum = 0L;
                        i++;
                        j++;
                    }else if (a[i] < b[j]){
                        asum += a[i];
                        i++;
                    }else{
                        bsum += b[j];
                        j++;
                    }
                }
            }
            if(asum > bsum) sum += asum;
            else sum += bsum;
            System.out.println(sum);
        }
    }
}
