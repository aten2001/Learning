
import java.util.*;
import java.io.*;

public class PowerSet {

  public static void main(String[] args){
    char[] set = { 'a', 'b', 'c' };

    int powerSetSize = (int) Math.pow(2,set.length);
    for(int i=0; i<powerSetSize; i++){
      String val = "";
      for(int j=0; j<set.length; j++){
        if((i  & (1<<j)) != 0) val += set[j]; // trick here is bit masking.. 1<<j ensure that we shift create a bit map with 1 set at index of set
        // and if that index has 1 in i (counter) that value is not 0 as in and operation rest all are masked to 0, 0& anything is 0
      }
      System.out.println(val);
    }


  }

}
