/*
 * https://www.hackerrank.com/challenges/fibonacci-modified
 */
import java.util.*;
import java.math.*;

public class FibboModified {

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        String first = sc.next();
        String second = sc.next();
        int term = sc.nextInt();

        BigInteger termFirst = new BigInteger(first);
        BigInteger termSecond = new BigInteger(second);
        for(int i=2; i<term; i++){
            BigInteger termNext = termFirst.add(termSecond.multiply(termSecond));
            termFirst = termSecond;
            termSecond = termNext;
        }
        System.out.println(termSecond);

    }


}
