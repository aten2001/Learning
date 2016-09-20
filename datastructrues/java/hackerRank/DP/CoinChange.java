
/*
 * https://www.hackerrank.com/challenges/coin-change
 */

import java.util.*;


public class CoinChange {


    public static void main(String[] args){
       Scanner sc = new Scanner(System.in);
       int sum = sc.nextInt();
       int N = sc.nextInt();
       if (N == 0){
           System.out.println(0);
       }
       int[] data = new int[N];
       Map<List<Integer>,Integer> memo = new HashMap<>();
       for(int i=0; i<N; i++){
           data[i] = sc.nextInt();
       }

       List<Integer> list = new ArrayList<>();
       if (sum == 0){
           System.out.println(0);
       }

       int output = recCompute(list,sum,data,memo);
       System.out.println(output);
    }

    static int recCompute(List<Integer> lst, int sum, int[]data, Map<List<Integer>,Integer>memo){
        if (sum == 0)              return 1;
        int output = 0;
        for(int i=0; i<data.length; i++){
            if (sum < data[i]) continue;
            List<Integer> newLst =  buildList(lst,data[i]);
            if (memo.containsKey(newLst)) continue;
            int val = recCompute(newLst,sum-data[i],data,memo);
            memo.put(newLst,val);
            output +=val;
        }
        return output;
    }

    static List<Integer> buildList(List<Integer> lst, int val){
        List<Integer> newLst = new ArrayList<>();
        boolean added = false;
        for(int c : lst){
            if (!added && val < c){
                newLst.add(val);
                added = true;
            }
            newLst.add(c);
        }
        if(!added) newLst.add(val);
        return newLst;
    }


}

