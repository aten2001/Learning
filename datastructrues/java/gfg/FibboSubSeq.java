import java.util.*;

public class FibboSubSeq {

   public static void main(String[] args){
       Scanner sc = new Scanner(System.in);
       int T = sc.nextInt();
       while(T-- > 0){
           int N = sc.nextInt();
           int[] data = new int[N];
           int max = -1;
           for(int i=0; i<N; i++){
               data[i] = sc.nextInt();
               if(max < data[i]) max = data[i];
           }

           Set<Integer> fib = fibbo(max);
           for(int i=0; i<N; i++){
               if(fib.contains(data[i])) System.out.print(data[i]+" ");
           }
           System.out.println();
       }
   }

   static Set<Integer> fibbo(int max){
       Set<Integer> output = new HashSet<>();
       if(max == 0) output.add(0);
       else if(max == 1) {
           output.add(0);
           output.add(1);
       } else{
           output.add(0);
           output.add(1);
           int a = 1, b = 1;
           while(a+b <= max){
               int temp = b;
               b = a + b;
               a = temp;
               output.add(b);
           }
       }
       return output;
   }
}
