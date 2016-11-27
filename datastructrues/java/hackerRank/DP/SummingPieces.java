
import java.util.*;
import java.math.*;
import java.io.*;


public class SummingPieces {

  public static class Node{
    public final int count;
    public final BigInteger sum;

    public Node(int count, BigInteger sum){
      this.count = count;
      this.sum = sum;
    }
  }

  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    int [] data = new int[N];
    Node[] memo = new Node[N];

    for(int i=0; i<N; i++){
      data[i] = sc.nextInt();
    }
    Node node = recSum(memo,data,0);
    System.out.println(node.sum.mod(BigInteger.valueOf(1000000007L)));
  }

  private static Node recSum(Node[] memo, int[] data, int i){
    if(i == data.length) return new Node(1,BigInteger.valueOf(0L));
    if(i == data.length-1) return new Node(1,BigInteger.valueOf(data[i]));
    if(memo[i] != null) return memo[i];
    BigInteger sum = BigInteger.valueOf(0L);
    BigInteger buff = BigInteger.valueOf(0L);
    int len = 1;
    int count = 0;
    for(int j=i; j<data.length; j++,len++){
      buff = buff.add(BigInteger.valueOf(data[j]));
      Node node = recSum(memo,data,j+1);
      BigInteger tmp = buff.multiply(BigInteger.valueOf(len)).multiply(BigInteger.valueOf(node.count));
      sum = sum.add(tmp);
      sum = sum.add(node.sum);
      count += node.count;
    }
    memo[i] = new Node(count,sum);
    return memo[i];
  }
}
