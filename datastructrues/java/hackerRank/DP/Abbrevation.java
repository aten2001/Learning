
import java.util.*;
import java.io.*;

public class Abbrevation {


    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());
        while(N-- > 0){
            String a = sc.nextLine();
            String b = sc.nextLine();
            Boolean[][] memo = new Boolean[a.length()][b.length()];
            boolean match = checkMatch(memo,a,b,0,0);
            if(match){
                System.out.println("YES");
            }else{
                System.out.println("NO");
            }
        }
    }

    private static boolean smallcase(char a){
        return a - 97 >=0;
    }

    private static boolean checkMatch(Boolean[][] memo, String a, String b, int i, int j){
        if(b.length() == j) return checkAllSmallCase(a.substring(i));
        if(a.length() == i) return false;
        if(memo[i][j] != null) return memo[i][j];

        if(smallcase(a.charAt(i))){ //small case
            if(caseInsensitiveMatch(a.charAt(i), b.charAt(j))){ //match
                memo[i][j] = checkMatch(memo,a,b,i+1,j+1);
                if(!memo[i][j]) memo[i][j] = checkMatch(memo,a,b,i+1,j);
            }else{ // mismatch
                memo[i][j] = checkMatch(memo,a,b,i+1,j);
            }
        }else{
            if(a.charAt(i) == b.charAt(j)){ //match
                memo[i][j] = checkMatch(memo,a,b,i+1,j+1);
            }else{
                memo[i][j] = checkMatch(memo,a,b,i+1,0); //reset to 0
            }
        }
        return memo[i][j];
    }

    private static boolean checkAllSmallCase(String subStr){
        boolean output = true;
        for(int i=0; i<subStr.length(); i++){
            if(subStr.charAt(i) - 97 >= 0) { //small case
                continue;
            }else{
                output = false;
                break;
            }
        }
        return output;
    }

    private static boolean caseInsensitiveMatch(char first, char second){
        int val = first - 97;
        if(val >= 0){
            int sval = second - 65;
            return val == sval;
        }else{
            return first == second;
        }
    }

}
