
import java.util.*;
import java.io.*;

public class TwoClosestStrings {


  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int T = sc.nextInt();
    while(T-- > 0){
      String input = sc.next();
      int N = input.length();
      int K = sc.nextInt();
      StringBuilder output = new StringBuilder();
      int i  = 0,j = 0;
      while(j < K){
        char val = input.charAt(i);
        if ('a' == val){
          int skip = i-j;
          if(skip < (N-K)){
            output.append('a');
            i++;
            continue;
          }
        }
        output.append('a' == val ? 'b' : 'a');
        i++;
        j++;
      }
      if(i < N) output.append(input.substring(i,N));
      System.out.println(output.toString());
    }
  }

}
