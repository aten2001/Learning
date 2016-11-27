import java.util.*;
import java.io.*;

public class StockMaximize2 {

  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int T = sc.nextInt();
    while(T-- > 0){
      int N = sc.nextInt();
      int [] data = new int[N];
      int [] sum = new int[N];
      int [] index = new int[N];
      int buff = 0;
      for(int i=0; i<N; i++){
        int val  = sc.nextInt();
        buff += val;
        data[i] = val;
        sum[i] = buff;
        index[i] = i;
      }
      indexsort(data,index);
      int profit = 0;
      for(int i=0; i<index.length; i++){
        int idx = index[i];
        int cp = sum[idx] - data[idx];
        int sp = (idx) * data[idx];
        if(sp < cp) continue;
        int p = sp - cp;
        profit = (profit < p) ? p : profit;
      }
      System.out.println(profit);
    }
  }

  private static void indexsort(int[]data, int[] index){


  }

}
