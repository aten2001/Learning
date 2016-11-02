
import java.util.*;
import java.io.*;

public class TugOfWar {

  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int[] data = new int[n];
    int sum = 0;
    for(int i=0; i<n; i++){
      int val = sc.nextInt();
      sum += val;
      data[i] = val;
    }
    int diff = subsetDiff(data,0,0,sum);
    System.out.println(diff);
  }

  private static int subsetDiff(int[] data,int i,int j,int sum ){
    if(j == (data.length/2)) return 0;
    if(i == data.length) return 0; // not equal subset
    int diff1 = sum - data[i] - subsetDiff(data,i+1,j+1,sum);
    int diff2 = sum - subsetDiff(data,i+1,j,sum);
    System.out.println(diff1+"_"+diff2);
    return (int) Math.min(Math.abs(diff1), Math.abs(diff2));
  }
}
