
/*
 * https://www.hackerrank.com/challenges/fair-cut
 */

import java.util.*;
import java.math.*;
import java.lang.*;


public class FairCut {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        int[] data = new int[N];
        String marked = ""; // better than boolean marked as this keep it immutable in recurisve calls
        Map<String,Integer> memo = new HashMap<String,Integer>();
        for(int i=0; i<N; i++){
            data[i] = sc.nextInt();
            marked += "0";
        }
        int output = recCheck(memo,data,marked,K);
        System.out.println(output);
    }


    private static int recCheck(Map<String,Integer> memo, int[] data, String marked,int k){
        if(memo.containsKey(marked)) return memo.get(marked);
        if (k == 0) {
            int output = computeUnfairness(data,marked);
            memo.put(marked,output);
            return output;
        }
        System.out.println(marked+" - "+k);
        int unfairness = Integer.MAX_VALUE;
        for(int i=0; i<data.length; i++){
            if(marked.charAt(i) == '0'){
                int val = recCheck(memo,data,updateMarked(marked,i),k-1);
                if (unfairness > val){
                    unfairness = val;
                }
            }
        }
        return unfairness;
    }

    private static String updateMarked(String marked, int i){
        String sub1 = (i == 0) ? "" : marked.substring(0,i);
        String sub2 = (i+1 == marked.length()) ? "" : marked.substring(i+1,marked.length());
        return sub1+"1"+sub2;
    }


    private static int computeUnfairness(int[]data, String marked){
        List<Integer> markedList  = new ArrayList<>();
        List<Integer> unmarkedList  = new ArrayList<>();
        for(int i=0; i<data.length; i++){
            if(marked.charAt(i) == '1'){
                markedList.add(i);
            }else{
                unmarkedList.add(i);
            }
        }

        int output = 0;
        for(int i : markedList){
            for(int j : unmarkedList){
                output += Math.abs(data[i] - data[j]);
            }
        }
        return output;
    }

}
