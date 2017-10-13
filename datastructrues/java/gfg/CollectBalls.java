import java.util.*;

//http://practice.geeksforgeeks.org/problems/geek-collects-the-balls/0
//
public class CollectBalls {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while(T-- > 0){
            int N = sc.nextInt();
            int M = sc.nextInt();
            int [] a = new int[N];
            int [] b = new int[M];

            Set<Integer> buff = new HashSet<>();
            for(int i=0; i<N; i++){
                a[i] = sc.nextInt();
                buff.add(a[i]);
            }

            Set<Integer> same = new HashSet<>();
            for(int i=0; i<M; i++){
                b[i] = sc.nextInt();
                if(buff.contains(b[i])){
                    same.add(b[i]);
                }
            }

            int sum = 0;

            int i = 0, j = 0;

            while( i < N || j < M){
                int left = 0;
                int right = 0;

                while( i < N ){
                    int val = a[i];
                    left += val;
                    i++;
                    if(same.contains(val)) break;
                }

                while( j < M ){
                    int val = b[j];
                    right += val;
                    j++;
                    if(same.contains(val)) break;
                }

                if(left > right) {
                    sum += left;
                }else {
                    sum += right;
                }
            }

            System.out.println(sum);
        }
    }

}

