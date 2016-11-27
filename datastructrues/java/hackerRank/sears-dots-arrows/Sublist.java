import java.util.*;
import java.io.*;


public class Sublist {

  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    int[] data = new int[N];
    for(int i=0; i<N; i++){
      data[i] = sc.nextInt();
    }

    int P = (int) Math.pow(2,N);

    Set<List<Integer>> duplicateCheckSet = new HashSet<>();
    for(int i=0; i<P; i++){
      List<Integer> subset = new ArrayList<>();
      for (int j=0; j<N; j++){
        if((i & (1 << j)) != 0){
          subset.add(data[j]);
        }
      }
      duplicateCheckSet.add(subset);
    }

    int count = duplicateCheckSet.size();

    System.out.println(count);
    //number of triplets is nC3
    long combinations = (count * (count-1) * (count-2))/6L;
    System.out.println(combinations % 1000000007);

  }

}


