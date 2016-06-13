
import java.util.Scanner;

public class DynamicPrograming1 {

  public static void main(String[] args){
    int [] noOfPages = new int[114];// as n is bounded to 113
    for(int i=0; i<114; i++) noOfPages[i] = Short.MAX_VALUE;
    noOfPages[0] = 0;
    int [] sp = { 10, 12 };
    int incr = 1;
    for(int i=10; i<114; i++){
      for(int j=0; j<sp.length; j++){
        if(sp[j] <= i && noOfPages[i-sp[j]]+incr < noOfPages[i]){
          noOfPages[i] = noOfPages[i-sp[j]]+incr;
        }
      }
    }
    
    Scanner sc = new Scanner(System.in);
    int T = sc.nextInt();
    while(T-- > 0){
      int N = sc.nextInt();
      System.out.println(noOfPages[N]);
    }
    sc.close();
  }
  
}

