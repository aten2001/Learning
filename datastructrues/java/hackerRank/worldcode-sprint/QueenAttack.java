//https://www.hackerrank.com/contests/world-codesprint-9/challenges/queens-attack-2
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class QueenAttack {


    static final int left = 1;
    static final int right = 2;
    static final int up = 3;
    static final int down = 4;
    static final int lu = 5;
    static final int ru = 6;
    static final int ld = 7;
    static final int rd = 8;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        Map<Integer,Set<Integer>> obstacle = new HashMap<>();
        int rQueen = in.nextInt();
        int cQueen = in.nextInt();

        for(int a0 = 0; a0 < k; a0++){
            int rObstacle = in.nextInt();
            int cObstacle = in.nextInt();
            Set<Integer> cols = obstacle.get(rObstacle);
            if(null == cols) {
                cols = new HashSet<>();
                obstacle.put(rObstacle,cols);
            }
            cols.add(cObstacle);
        }

        long output = 0L;
        for(int dir=1; dir<9; dir++){
            int r = rQueen, c = cQueen;
            long count = -1L;
            do{
                count++;
                if(dir == left){
                    c = c-1;
                }else if(dir == right){
                    c = c+1;
                }else if(dir == up){
                    r = r+1;
                }else if(dir == down){
                    r = r-1;
                }else if(dir == lu){
                    r = r+1;
                    c = c-1;
                }else if(dir == ru){
                    r = r+1;
                    c = c+1;
                }else if(dir == ld){
                    r = r-1;
                    c = c-1;
                }else if(dir == rd){
                    r = r-1;
                    c = c+1;
                }
            }while(notTerminal(n,obstacle,r,c));
            output += count;
        }
        System.out.println(output);
    }


    private static boolean notTerminal(int n,
            Map<Integer,Set<Integer>> obstacle,
            int r, int c){
        if(r < 1 || r > n) return false;
        if(c < 1 || c > n) return false;
        Set<Integer> cols = obstacle.get(r);
        if(cols == null) return true;
        if(cols.contains(c)) return false;
        return true;
    }
}

