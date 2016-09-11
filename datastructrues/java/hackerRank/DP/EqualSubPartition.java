
import java.util.*;
import java.math.*;


public class EqualSubPartition {

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while(T-- > 0){
            int N = sc.nextInt();
            int[] data = new int[N];
            for(int i=0; i<N; i++) data[i] = sc.nextInt();
            Arrays.sort(data); //sorts array
            int[] aux = new int[data.length];
            for(int i=0,j=N-1; i<N; i++,j--) aux[i] = data[j];
            int score = computeScore(0,aux,data,0,N);
            System.out.println(score);
        }
    }

    static int computeScore(int sum, int[]data, int[] aux, int start, int end){
        int size = end - start;
        if (size <= 1) return sum;
        int lo =start; int hi = end-1;
        long lsum =0L; long hsum = 0L;
        for (int i=start; i<size; i++){
            if (lsum < hsum){
                aux[lo++] = data[i];
                lsum += data[i];
            }else if(hsum > lsum){
                aux[hi--] = data[i];
                hsum += data[i];
            }else{
                int fsize = lo - start;
                int ssize = end - hi;
                if (fsize > ssize){
                    aux[hi--] = data[i];
                    hsum += data[i];
                }else{
                    aux[lo++] = data[i];
                    lsum += data[i];
                }
            }
        }
        System.out.println(start+" "+lo+" "+end);
        if (hsum != lsum){
            return sum;
        }else {
            return Math.max(computeScore(sum+1,aux,data,start,lo), computeScore(sum+1,aux,data,lo,end));
        }
    }


}
