import java.util.*;
import java.io.*;

public class ReducedString {

  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    String input = sc.next();
    int N = input.length();
    String output = reduce(input,0,N);
    output = output.length() == 0 ? "Empty String" : output;
    System.out.println(output);
  }

  private static String reduce(String input, int i, int N){
    if(i == N-1) return input.charAt(i)+"";
    char ch = input.charAt(i);
    String val = reduce(input,i+1,N);
    if(val.length() == 0)   return ch+val;
    else if(ch == val.charAt(0)) return val.substring(1);
    else return ch+val;
  }
}
