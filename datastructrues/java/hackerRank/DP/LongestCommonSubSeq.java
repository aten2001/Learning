
import java.util.*;
import java.math.*;


public class LongestCommonSubSeq {


    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        int [] first = new int[n];
        int [] second = new int[m];
        String[][] memo = new String[n][m];
        

        for(int i=0; i<n; i++) first[i] = sc.nextInt();
        for(int i=0; i<m; i++) second[i] = sc.nextInt();

        String output = findSubSeq(memo,first,second,0,0);

        System.out.println(output);

    }


    private static String findSubSeq(String[][] memo, int[] first, int[] second,
            int i, int j ){
        String output = "";
        if (i == first.length || j == second.length) return output;
        if (memo[i][j] != null) return memo[i][j];

        if (first[i] == second[j]){
            output += first[i]+ " ";
            output += findSubSeq(memo,first,second,i+1,j+1);
        }else{
            String buff = findSubSeq(memo,first,second,i+1,j);
            output = findSubSeq(memo,first,second,i,j+1);
            if(buff.length() > output.length()) output = buff;
        }
        System.out.println(memo[i][j]+"_"+i+"_"+j+"_"+output);
        memo[i][j] = output;
        return output;
    }
}
