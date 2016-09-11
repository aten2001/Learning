 
/*
 * The change making problem is an optimization problem that asks "What is the minimum number of coins I need to make up a specific total?"
 *
 * The input to the Change Making Problem is a sequence of positive integers [d1, d2, d3 ... dn] and T, where di represents a coin denomination and T is the target amount. Assuming an unlimited supply of coins of each denomination, we need to find the number of coins N required to form the given amount. An extra effort would be to find the exact coins to build up the amount.
 */


public class ChangeMaking{

    public static void main(String[] args){
        int sum = 17;
        int[] coins = { 1,2,5,7 };
        int memo [] = new int[sum+1];
        for(int i=0; i<sum+1; i++) memo[i] = -1;

        int count = topDown(coins, memo, sum, 0);

        System.out.println(count);
    }

    private static int topDown(int[] coins, int[] memo, int sum, int count){
        if(sum == 0)         return count;
        if(memo[sum] != -1)  return memo[sum];
        int buff = Integer.MAX_VALUE;
        for(int i=0; i<coins.length; i++){
            int tmpSum = sum - coins[i];
            if(tmpSum >= 0){
                int tmpCount = topDown(coins, memo,tmpSum,1+count);
                if(tmpCount > 0 && tmpCount < buff){
                    buff = tmpCount;
                }
            }
        }
        int output = (buff == Integer.MAX_VALUE)? -2 : buff;
        //memo[sum] = output;
        return output;
    }
}
