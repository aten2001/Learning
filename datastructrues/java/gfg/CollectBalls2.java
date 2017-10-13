import java.util.*;

//http://practice.geeksforgeeks.org/problems/geek-collects-the-balls/0
//
public class CollectBalls2 {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while(T-- > 0){
            int N = sc.nextInt();
            int M = sc.nextInt();
            int [] a = new int[N];
            int [] b = new int[M];

            for(int i=0; i<N; i++){
                a[i] = sc.nextInt();
            }

            for(int i=0; i<M; i++){
                b[i] = sc.nextInt();
            }

            Arrays.sort(a);
            Arrays.sort(b);


            long sum = 0L;

            int i = 0, j = 0;

            long left = 0L;
            long right = 0L;
            while( i < N || j < M){

                while(i < N && (j > M || a[i] < b[j])){
                    left += a[i];
                    i++;
                }

                while(j < M && (i > N || b[j] < a[i])){
                    right += b[j];
                    j++;
                }

                if(a[i] == b[j]){

                    if(left > right) {
                        sum += left + a[i];
                    }else {
                        sum += right + b[j];
                    }

                    i++;
                    j++;
                    left = 0L;
                    right = 0L;
                }
            }

            System.out.println(sum);
        }
    }

}

