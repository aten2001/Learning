

public class InsertionSort {

  public void sort(int[] data){

    for(int i=1; i<data.length; i++){
      for(int j=i; j>0 && data[j] < data[j-1]; j--){
        swap(data,j,j-1);
      }
    }
  }

  private void swap(int[] data, int i, int j){
    int buff = data[i];
    data[i] = data[j];
    data[j] = buff;
  }

}
