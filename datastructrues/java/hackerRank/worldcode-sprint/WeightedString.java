/*
https://www.hackerrank.com/contests/world-codesprint-9/challenges/weighted-uniform-string
*/
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class WeightedString {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        Set<Integer> possibleWeights = processInput(s);
        int n = in.nextInt();
        for(int a0 = 0; a0 < n; a0++){
            int x = in.nextInt();
            if(possibleWeights.contains(x)) System.out.println("Yes");
            else System.out.println("No");
        }
    }
    
    private static Set<Integer> processInput(String input){
       Map<String,Integer> map = new HashMap<>();
       StringBuilder buffer = new StringBuilder();
       char prev = input.charAt(0);
       buffer.append(prev);
       int weight = prev-96;
       map.put(buffer.toString(), weight);
       Set<Integer> output = new HashSet<>();
       output.add(weight);
       for(int i=1; i<input.length(); i++){
           char ch = input.charAt(i);
           if(prev == ch){
               int oldWt = map.get(buffer.toString());
               int wt = oldWt + ch - 96;
               buffer.append(ch);
               output.add(wt);
               map.put(buffer.toString(),wt);
           }else{
               prev = ch;
               buffer = new StringBuilder();
               buffer.append(prev);
               output.add(prev-96);
               map.put(buffer.toString(),prev-96);
           }
       }
       return output;
    }
}
