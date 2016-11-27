import java.util.*;
import java.io.*;

public class StockMaximize {

  private static final int BUY = 0;
  private static final int NOTHING = 1;
  private static final int SELL_ALL = 2;

  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int T = sc.nextInt();
    while(T-- > 0){
      int N = sc.nextInt();
      int [] data = new int[N];
      Map<Integer,Integer> [] memo = new HashMap[N];
      for(int i=0; i<N; i++){
        data[i] = sc.nextInt();
        memo[i] = new HashMap<>();
      }
      int maxProfit = recCompute(memo,data,0,0);
      System.out.println(maxProfit);
    }
  }

  private static int recCompute(Map<Integer,Integer>[] memo, int[] data, int i, int stocks){
    if(i == data.length) return 0;
    if(memo[i].containsKey(stocks))  return memo[i].get(stocks);
    int nothingProfit = recCompute(memo,data,i+1,stocks); // doNothing
    int buyProfit = recCompute(memo,data,i+1,stocks+1) - data[i]; // Buy one
    int profit = buyProfit > nothingProfit ? buyProfit : nothingProfit;
    if(stocks > 0){
      int sellAllProfit = recCompute(memo,data,i+1,0) + (stocks*data[i]); // sellAll
      profit = (sellAllProfit > profit) ? sellAllProfit : profit;
    }
    memo[i].put(stocks,profit);
    return profit;
  }

}
