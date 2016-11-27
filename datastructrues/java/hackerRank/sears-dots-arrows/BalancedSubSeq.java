import java.util.*;
import java.io.*;

public class BalancedSubSeq {

  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int T = sc.nextInt();
    while(T-- > 0){
      String input = sc.next();
      int push = 0; int count = 0;
      for(int i=0; i<input.length(); i++){
        char ch = input.charAt(i);
        if(ch == '(') push++;
        else if(ch == ')' && push > 0){
          push--;
          count+=2;
        }
      }
      System.out.println(count);
    }
  }
}
