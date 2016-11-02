import java.util.*;
import java.io.*;
import java.math.*;

public class PopsicleStickMountain {

  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int t = sc.nextInt();
    while(t-- > 0){
      int n  = sc.nextInt();
      n = n % 2 != 0 ? n-1 : n;
      BigInteger[][] memo = new BigInteger[n+1][n+1];
      BigInteger count = computeMountains(memo,n);
      System.out.println(count);
    }

  }

  public static BigInteger computeMountains(BigInteger[][] memo,int N){
    if(N < 2)  return BigInteger.valueOf(0L);
    if(N == 2) return possibleOpts(memo,N,0,N/2);
    else return possibleOpts(memo,N,0,N/2).add(computeMountains(memo,N-2));
  }

  private static BigInteger possibleOpts(BigInteger[][] memo, int N, int ht, int htTh){
    if(ht < 0) return BigInteger.valueOf(0L); // dont start with negative h
    if(ht > htTh) return BigInteger.valueOf(0L); //case where it will not touch the ground
    if(memo[N][ht] != null) return memo[N][ht];
    BigInteger val;
    if(N == 0){
      val = (ht == 0) ?  BigInteger.valueOf(1L) : BigInteger.valueOf(0L);
    }else{
      val = possibleOpts(memo,N-1,ht+1,htTh).add(possibleOpts(memo,N-1,ht-1,htTh));
    }
    memo[N][ht] = val;
    return val;
  }
}
