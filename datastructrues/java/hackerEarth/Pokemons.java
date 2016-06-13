
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Pokemon {

  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int T = sc.nextInt();
    while(T-- > 0){
      int N = sc.nextInt();
      Map<Integer,Integer> countMap = new HashMap<Integer,Integer>();
      int food = 0 ;
      while(N-- >0){
        int item = sc.nextInt();

        int oldCount = countMap.getOrDefault(item, 0);
        countMap.put(item, oldCount+1);

        int pokemon = sc.nextInt();
        int oldVal = countMap.getOrDefault(pokemon, 0);
        if(oldVal > 0){
          countMap.put(pokemon, oldVal-1);
        }else{
          food++;
        }

      }
      System.out.println(food);
    }
    sc.close();
  }
}
