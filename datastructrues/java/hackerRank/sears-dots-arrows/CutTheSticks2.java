
import java.util.*;
import java.io.*;

// attempt to implement this with Heap
public class CutTheSticks2 {

  public static class MinPQ{

    public int[] data;
    public int index;

    public MinPQ(int N){
      this.data = new int[N+1];
      this.index=1;
    }

    public int size(){
      return this.index-1;
    }

    public void add(int val){
      this.data[index] = val;
      swim(index++);
    }

    public int delMin(){
      int val = this.data[1];
      this.data[1] = this.data[--index];
      sink(1);
      return val;
    }

    private void swim(int i){
      while(i/2 > 0){
        if(less(i,i/2)){
          swap(i,i/2);
          i = i/2;
        }else{
          break;
        }
      }
    }

    private void sink(int i){
      while(i < index/2){
        int val = 2*i;
        if(val+1 < index){
          val = less(val,val+1) ? val : val+1;
        }
        if(less(val,i)){
          swap(val,i);
          i = val;
        }else{
          break;
        }
      }
    }

    private boolean less(int i, int j){
      return data[i] < data[j];
    }

    private void swap(int i, int j){
      int buff = data[i];
      data[i] = data[j];
      data[j] = buff;
    }
  }

  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    MinPQ pq = new MinPQ(N);
    while(N-- > 0){
      pq.add(sc.nextInt());
    }

    int val = 0;
    while(pq.size() > 0){
      int buff = pq.delMin();
      if (buff == val) continue; // same value
      val += (buff-val);
      System.out.println(pq.size()+1); // +1 to account for delMin element
    }
  }
}
