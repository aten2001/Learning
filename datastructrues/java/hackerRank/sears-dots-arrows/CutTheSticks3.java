
import java.util.*;
import java.io.*;

// attempt to implement this with Heap
public class CutTheSticks3 {

  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    int[] data  = new int[N];
    for(int i=0; i<N; i++){
      data[i] = sc.nextInt();
    }

    Arrays.sort(data);
    int val = 0;
    for(int i=0; i<N; i++){
      int buff = data[i];
      if (buff == val) continue; // same value
      System.out.println(N-i);
      val += (buff-val);
    }
  }
}
