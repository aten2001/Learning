

public class MaxPQ {

  private int[] data;
  private int N;

  public MaxPQ(int len){
    this.data = new int[len+1];
    this.N=0;
  }

  public void add(int val){
    this.data[++N]=val;
    swim(N);
  }

  public int delMax(){
    int val = data[1];
    swap(data,1,N);
    data[N--]=null;
    sink(1);
    return val;
  }

  private void sink(int indx){
    int child = (2 * k) + 1;
    while(child <= N){
      child = (data[child] < data[child-1]) ? child - 1 : child;
      if(data[child] > data[indx]){
        swap(data,child,indx);
        indx = child;
        child = (2 * child) + 1;
      }else{
        break;
      }
    }
  }

  private void swim(int k){
   int parent = k/2;
   while(parent >=1){
     if(data[parent] < data[k]){
       swap(data,parent,k);
       k = parent;
       parent = parent/2;
     }
   }
  }

  private void swimRec(int k){
    if(k/2 < 1) return;
    if(data[k/2] < data[k]){
      swap(data,k,k/2);
      swimRec(k/2);
    }
  }

}
