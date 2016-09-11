/*
 * https://www.hackerrank.com/challenges/mandragora
 */

import java.util.*;
import java.math.*;

public class Mandragora {


    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while(T-- > 0){
            int N = sc.nextInt();
            int [] data = new int[N];
            String marked = "";
            for(int i=0; i<N; i++){
                data[i] = sc.nextInt();
                marked += "0";
            }
            Map<String,Long> memo = new HashMap<String,Long>();
            long point = recurCheck(1,0L,data,marked,memo);
            System.out.println(point);
        }
    }

    private static long recurCheck(int S, long P, int[] data, String marked,
            Map<String,Long> memo){
        if (marked.indexOf('0') == -1) return P;
        String key = S+"_"+P+"_"+marked;

        if (memo.containsKey(key)) return memo.get(key);
        memo.put(key,P); //update memo

        long maxPoint = -1L;
        for(int i=0; i<data.length; i++){
            if(marked.charAt(i) == '0'){
                int health = data[i];
                String sub1 = (i == 0) ? "" : marked.substring(0,i);
                String sub2 = (i == (marked.length()-1)) ? "" : marked.substring(i+1,marked.length());
                String newMarked = sub1+"1"+sub2;
                long P1 = recurCheck(S+1,P,data,newMarked,memo);
                long P2 = recurCheck(S,P+(health*S),data,newMarked,memo);
                //System.out.println(newMarked+" - "+P1+" - "+P2);
                long point = (P1 < P2) ? P2 : P1;
                if (maxPoint < point){
                    maxPoint = point;
                }
            }
        }
        return maxPoint;
    }
}
