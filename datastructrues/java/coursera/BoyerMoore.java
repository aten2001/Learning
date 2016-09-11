import java.util.Scanner;

public class BoyerMoore {

  private final int[] jump;
  private final int M;
  private final String patt;

  public BoyerMoore(String patt, int R){
    this.jump = new int[R];
    this.M = patt.length();
    this.patt=patt;

    for(int i=0; i<R; i++){
      this.jump[i] = -1;
    }

    for(int i=0; i<M; i++){
      this.jump[patt.charAt(i)] = i;
    }
  }

  public void substrings(String data){
    int skip;
    for(int i=0; i<data.length()-M; i+=skip){
      skip=1;
      int j;
      for(j=M-1; j>=0; j--){
        if(patt.charAt(j) != data.charAt(i+j)){
          skip = Math.max(1,j - jump[data.charAt(i+j)]); //avoid negatives
          break;
        }
      }
      if(j < 0) System.out.println(data.substring(i,i+M));
    }
  }

  public static void main(String[] args){
    BoyerMoore bm = new BoyerMoore(args[0],256);
    Scanner sc = new Scanner(System.in);
    while(sc.hasNext()){
      bm.substrings(sc.next());
    }
  }

}
