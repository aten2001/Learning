
public class HeapSort {

  public static void sort(int[] data){
    int N = data.length+1;
    // heap order the array
    for(int i=N/2; i<N; i++){
      sink(data, i);
    }

    while(N-- > 0){ // sort array
      swap(data,1,N+1);
      sink(data,1);
    }

  }

  private void sink(int[] data,int k){
    while( 2*k <= N){
      int child = less(2*k, (2*k)+1) ? (2*k)+1 : 2*k;
      if(less(data, k,child)) swap(data,k,child);
      k = child;
    }
  }

  private void swap(int[] data, int a, int b){
    int buff = data[a-1];
    data[a-1] = data[b-1];
    data[b-1] = buff;
  }

  private void less(int[] data, int a, int b){
    return (data[a-1] < data[b-1]);
  }

}
