import java.util.*;
import java.io.*;

public class ConnectionQueries{

  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    int Q = sc.nextInt();
    int data[] = new int[N];
    for(int i=0; i<N; i++){
      data[i] = sc.nextInt();
    }

    while(Q-- > 0){
      int x = sc.nextInt() - 1;
      int y = sc.nextInt() - 1;
      boolean[] marked = new boolean[N+2];
      int join = 0;
      for(int i=x; i<=y; i++){
        int val = data[i];
        if(marked[val-1]) join++;
        if(marked[val+1]) join++;
        marked[val] = true;
      }
      System.out.println(y-x+1-join);
    }
  }

}

