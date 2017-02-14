//https://www.hackerrank.com/contests/world-codesprint-9/challenges/two-subarrays


import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class TwoSubArrays {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] a = new int[n];
        for(int a_i=0; a_i < n; a_i++){
            a[a_i] = in.nextInt();
        }

        int g = 0;
        for(int z=n-1; z>0; z--){
            for(int i=0; i+z<n; i++){
                int a = i;
                int b = i+z;
                
            }
        }
    }
}

