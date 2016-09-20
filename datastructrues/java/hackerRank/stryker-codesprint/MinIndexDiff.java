

/*
 * https://www.hackerrank.com/contests/stryker-codesprint/challenges/minimum-index-difference
 */

import java.util.*;


public class MinIndexDiff {


    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int [] first = new int[N];

        Map<Integer,Integer> indexDiffMap = new HashMap<>();
        int minDiff = Integer.MAX_VALUE; 
        int minIndex = -1;

        for(int i=0; i<N; i++){
            int val = sc.nextInt();
            first[i] = val;
            indexDiffMap.put(val,i);
        }

        for(int i=0; i<N; i++){
            int val = sc.nextInt();
            int firstIndex = indexDiffMap.get(val);
            int diff = Math.abs(firstIndex - i);
            //apply rules
            if (diff < minDiff){
                minIndex = firstIndex;
                minDiff = diff;
            }else if(diff == minDiff){
                if(first[firstIndex] < first[minIndex]){
                    minIndex = firstIndex;
                }
            }
        }

        System.out.println(first[minIndex]);

    }


}
