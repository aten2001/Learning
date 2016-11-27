
import java.util.*;
import java.io.*;

public class CountYourProgression {

  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int[] data = new int[n];
    for(int i=0; i<n; i++){
      data[i] = sc.nextInt();
    }
    int prev = 101,diff = 101; // value greater that max == 100
    Map<Integer,Long>[][] memo = new HashMap[n][102];
    long output = (long) n + 1L + numberOfSubSeq(memo,data,0,n,prev,diff);
    System.out.println(output % 1000000009);
  }

  private static long numberOfSubSeq(Map<Integer,Long>[][] memo, int[] data, int i, int n, int prev,int diff){
    if(i == n) return 0;
    if(memo[i][prev] != null && memo[i][prev].containsKey(diff)) return memo[i][prev].get(diff);
    long count = 0L;
    if(prev == 101) { //not assigned yet
      long opt1 = numberOfSubSeq(memo,data,i+1,n,data[i],diff);
      long opt2 = numberOfSubSeq(memo,data,i+1,n,prev,diff);
      count = opt1 + opt2;
    }else if (diff == 101){
      int newDiff = data[i] - prev;
      long opt1 = 1L +  numberOfSubSeq(memo,data,i+1,n,data[i],newDiff);
      long opt2 = numberOfSubSeq(memo,data,i+1,n,prev,diff);
      count = opt1 + opt2;
    }else{
      if((data[i] - prev) == diff){
        count =  1L + numberOfSubSeq(memo,data,i+1,n,data[i],diff);
      }else{
        count =  numberOfSubSeq(memo,data,i+1,n,prev,diff);
      }
    }
    if(memo[i][prev] == null){
      memo[i][prev] = new HashMap<Integer,Long>();
    }
    memo[i][prev].put(diff,count);
    return count;
  }

}
