

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class WeightedStringSecond {

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
        int i=0;
        char prev = input.charAt(i);
        int wt = prev - 96;
        Set<Integer> output = new HashSet<>();
        output.add(wt);
        while(++i < input.length()){
            char ch = input.charAt(i);
            if(prev == ch) wt += (ch - 96);
            else {
                prev = ch;
                wt = prev - 96;
            }
            output.add(wt);
        }
        return output;
    }

}
