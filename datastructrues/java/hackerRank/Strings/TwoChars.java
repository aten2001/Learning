import java.util.*;
import java.io.*;


public class TwoChars {

  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    if (N == 1) {
      System.out.println(0);
    }else{
      String input = sc.next();
      int len = findL(input,"","",0,N);
      System.out.println(len);
    }
  }

  private static int findL(String input,String output,String del, int i, int N){
    if(i == N){
      //System.out.println(output);
      return output.length() < 2 ? 0 : output.length();
    }
    char elm = input.charAt(i);
    if(del.indexOf(elm) != -1) return findL(input,output,del,i+1,N);
    if(output.length() >= 2){
      if(output.indexOf(elm) == -1){
        return findL(input,output,del+elm,i+1,N);
      } else if(output.charAt(output.length()-1) == elm){
        return 0; //case of consequtive
      } else{
        return findL(input,output+elm,del,i+1,N);
      }
    }else{
      if(output.indexOf(elm) == -1) {
        int len1 = findL(input,output+elm,del,i+1,N);
        int len2 = findL(input,output,del+elm,i+1,N);
        return Math.max(len1,len2);
      } else {
        return 0;
      }
    }
  }

}
