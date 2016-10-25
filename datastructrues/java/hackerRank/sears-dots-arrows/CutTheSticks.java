import java.io.*;
import java.util.*;

public class CutTheSticks {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int [] data  = new int[N];
        int min = Integer.MAX_VALUE;
        int size = 0;
        for(int i=0; i<N; i++){
            int val = sc.nextInt();
            data[i] = val;
            if(val == 0) continue;
            if(min > val) min = val;
            size++;
        }
        while(size-- > 0){
            int cut = 0;
            int newMin = Integer.MAX_VALUE;
            int newSize = 0;
            for(int i=0; i<N; i++){
               int val = data[i];
               if(val  == 0) continue;
               val = val - min;
               data[i] = val;
               cut++;
               if(val == 0) continue;
               if(newMin > val){
                   newMin = val;
               }
               newSize++;
            }
            min = newMin;
            size = newSize;
            System.out.println(cut);
        }
    }
}
