

import java.util.Scanner;

public class ServiceLane {
    
    private static void computeMaxVehicle(int[] width, int i, int j){
       int[] aux = new int[width.length];
       for(int k=i; k<=j; k++) aux[k] = width[k];
       int[] mids  = partiton(aux,i,j); 
       while(mids[0] > i+1){
           mids = partiton(aux,i,mids[0]);
       }
       System.out.println(aux[i]);
    }
    
    private static int[] partiton(int[] aux, int lo, int hi){
        int i=lo+1,lt=lo,j=hi;
        while(i <= j){
            int cmp = aux[i] - aux[lo];
            if(cmp < 0) swap(aux,i++,lo++);
            else if (cmp > 0) swap(aux,i,j--);
            else i++;
        }
        int [] mids = {j, lo};
        return mids;
    }
    
    private static void swap(int[] data, int i, int j){
        int buff = data[i];
        data[i] = data[j];
        data[j] = buff;
    }
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int T = sc.nextInt();
        int width[] = new int[N];
        int i = 0;
        while(i < N){
            width[i++] = sc.nextInt();
        }
        while(T-- >0){
            computeMaxVehicle(width,sc.nextInt(),sc.nextInt());
        }
    }
}
