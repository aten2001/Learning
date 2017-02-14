//https://www.hackerrank.com/contests/rookierank-2/challenges/prefix-neighbors

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class PrefixNeighbors {

    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[] data = new String[n];
        Arrays.sort(data);
        for
        System.out.println(Arrays.toString(data));
        int benifit = maxBenifit(data,data[0],1);
        System.out.println(benifit);
    }

    private static int getIdx(String s, int i){
        if(i >= s.length()) return 0;
        char ch = s.charAt(i);
        return ch - 65;
    }

    private static int maxBenifit(String[] data,String first, int next){
        if(next  == data.length) return asciScore(first);
        if(isPrefix(first, data[next])){
            int oneOutcome = maxBenifit(data,first,next+1);
            int secondOutcome = maxBenifit(data,data[next],next+1);
            return Math.max(oneOutcome,secondOutcome);
        }else{
            return asciScore(first) + maxBenifit(data,data[next],next+1);
        }
    }

    private static int asciScore(String val){
        int output = 0;
        for(int i=0; i<val.length(); i++){
            output += val.charAt(i);
        }
        System.out.println(val+"_"+output);
        return output;
    }

    private static boolean isPrefix(String first, String second){
        for(int i=0; i<first.length(); i++){
            if(first.charAt(i) == second.charAt(i)) continue;
            else return false;
        }
        return true;
    }
}
