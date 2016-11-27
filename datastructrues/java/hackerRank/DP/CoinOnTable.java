
import java.util.*;
import java.io.*;


public class CoinOnTable {

  private static final char[]possibleMoves = { 'U', 'L', 'R', 'D' };

  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    int M = sc.nextInt();
    int K = sc.nextInt();
    int size = N*M; // change2D problem to 1D array
    char[] data  = new char[size];
    int dest = -1;
    for(int i=0; i<N; i++){
      String rowStr = sc.next();
      for(int j=0; j<M; j++){
        int row = (i*M) + j;
        char ch = rowStr.charAt(j);
        if(ch == '*') dest = row;
        data[row] = ch;
      }
    }
    int minChanges = computeMinChanges(data,0,dest,0,K,M);
    System.out.println(minChanges);
  }

  private static int computeMinChanges(char[] data, int i,int dest, int moves,int K,int M){
    if(moves > K) return -1;                 // moves exceeded
    int changes = Integer.MAX_VALUE;
    if(i != dest){
      for(char pos : possibleMoves){
        int nextIndex = computeNext(pos,i,M);
        if(i >= data.length || i < 0) continue; // going off the board
        int ch = computeMinChanges(data,nextIndex,dest,moves+1,K,M);
        if(ch == -1) continue;
        if(pos != data[i])  ch += 1; //increment change made
        changes = (changes > ch)?  ch : changes;
      }
      return (changes == Integer.MAX_VALUE) ? -1 : changes; // assuming we dont have changes as big as Integer.MAX_VALUE
    }else{  //reached destination
      return 0;
    }
  }

  private static int computeNext(char pos, int i, int cols){
    if(pos == 'U') return i - cols;
    if(pos == 'L') return i - 1;
    if(pos == 'R') return i + 1;
    if(pos == 'D') return i + cols;
    throw new RuntimeException("Invalid char");
  }

}
