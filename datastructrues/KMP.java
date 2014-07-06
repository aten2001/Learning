import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;

public class KMP {

  private final int[][] dfa;
  private final int M;
  private Queue<Integer> queue;
  public KMP(String patt, int R){
    this.queue = new LinkedList<>(); 
    this.M = patt.length();
    this.dfa = new int[R][M];
    this.dfa[patt.charAt(0)][0]=1;
    for(int i=1, X=0; i<M; i++){
      for(int j=0; j<R; j++)
        this.dfa[j][i] = this.dfa[X][i]; //mark all transitions as per X

      this.dfa[patt.charAt(i)][i] = i+1; //match transition
      X = this.dfa[patt.charAt(i)][X]; // update X state
    }
  }

  public void substrings(String data){
    for(int i=0,j=0; i<data.length(); i++){
      j = this.dfa[data.charAt(i)][j];
      if(j == M){
        System.out.println(data); //print line 
        queue.add(i-M);
        j=0;
      }
    }
  }

  public static void main(String[] args){
    System.out.println(args[0]);
    KMP kmp = new KMP(args[0], 256);
    Scanner sc = new Scanner(System.in);
    while(sc.hasNext()){
      kmp.substrings(sc.next());
    }
  }
}
