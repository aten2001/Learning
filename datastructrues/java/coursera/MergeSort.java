

public class MergeSort {

  private static final int CUT_OFF = 10;
  public void sort(int[] data){
    int [] aux = new int[data.length];
    recSort(data,aux,0,data.length-1);
  }

  private void recSort(int[] data, int[] aux, int lo, int hi){
    if(hi - lo <= CUT_OFF) Insertion.sort(data,lo,hi);
    int mid = (lo+hi)/2;
    recSort(data,aux,lo,mid);
    recSort(data,aux,mid+1,hi);
    merge(data,aux,lo,mid,hi);
  }

  private void merge(int[] data, int[] aux, int lo, int mid, int hi){
    if(data[mid] < data[mid+1]) return; // alreday merged
    for(int i=lo; i<=hi; i++) aux[i] = data[i];
    int i=lo, j= mid+1;
    for(int k=lo; k<hi; k++){
      if(i > mid) data[k] = aux[j++];
      else if(j > hi) data[k] = aux[i++];
      else if(less(aux,i,j)) data[k] = aux[i++];
      else data[k] = aux[j++];
    }
  }

  public void buSort(int[] data){
    int[] aux = new int[data.length];
    for(int i=1; i<data.length; i+=i){
      for(int j=0; j<data.length-i; j+=i+i){
        merge(data,aux,j,j+i-1,Math.min(data.length,j+i+i-1));
      }
    }
  }

}

