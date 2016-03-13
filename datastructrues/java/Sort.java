

public class Sort {

  public static void insertionSort(int[] data){
    for(int i=1; i<data.length; i++){
      for(int j=i; j>0; j--){
        if(data[j] < data[j-1]) {
          int temp = data[j];
          data[j] = data[j-1];
          data[j-1] = temp;
        } else {
          break;
        }
      }
    }
  }

  public static void shellSort(int[] data){
    int h = 1;
    while(h < data.length/3) h = (3 * h) + 1; // 1 4 13

    while(h > 0){
      for(int i=h; i<data.length; i++){
        for(int j=i; j>=h; j-=h){
          if(data[j] < data[j-h]){
            //swap
          } else {
            break;
          }
        }
      }
      h /= 3;
    }


  }

  public static void mergeSort(int[] data){
    int [] aux = new int[data.length];
    recMerge(data,aux,0,data.length-1);
  }

  private static void recMerge(int[] data, int[] aux, int low, int hi){
    if (lo >= hi) return;
    int mid = (lo + hi) / 2;
    recMerge(data,aux,lo,mid);
    recMerge(data,aux,mid+1,hi);
    merge(data,aux,lo,mid,hi);
  }

  private static void merge(int [] data, int[] aux, int lo, int mid, int hi){

    for(int i=lo; i<=hi; i++) aux[i] = data[i];

    int i=lo; int j= mid+1;
    for(int k=lo; k<=hi; k++){
      if(i > mid) data[k] = aux[j++];
      else if(j > hi) data[k] = aux[i++];
      else if(aux[i] <= aux[j]) data[k] = aux[i++];
      else data[k] = aux[j++];
    }
  }

  public static void buMergetSort(int[] data){

  }

  public static void quickSort(int[] data){

  }

  public static void threeWayQuickSort(int[] data){

  }

  public static void heapSort(int[] data){

  }

  public static void lsdsort(String[] data){

  }

  public static void lsdRadixSort(int[] data){

  }

  public static void msdSort(String[] data){

  }

  public static void msdQuickSort(String[] data){

  }
}
