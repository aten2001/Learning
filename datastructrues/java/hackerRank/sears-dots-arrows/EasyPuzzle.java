import java.util.*;
import java.io.*;

public class EasyPuzzle {


  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int T = sc.nextInt();
    while(T-- > 0){
      boolean divisible = false;
      int N = sc.nextInt();
      int K = sc.nextInt();
      for(int i=0; i<N; i++){
        int val = sc.nextInt();
        if(val % K == 0){
          divisible = true;
        }
      }
      if(divisible) System.out.println("YES");
      else          System.out.println("NO");
    }
  }

}
