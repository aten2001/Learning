
import java.util.*;
import java.io.*;

public class HikingSelfies {

  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int x = sc.nextInt();
    int combinations = ((int) Math.pow(2,n)) - 1; // -1 to remove none selected
    System.out.println((int) Math.abs((combinations-x)));
  }

}
