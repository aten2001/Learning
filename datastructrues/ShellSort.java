

public class ShellSort {

  public void sort(int [] data){
    int h = 1;

    while (h < data.length/3) h = (3*h) + 1;

    while (h > 0){
      for(int i=h; i<data.length; i++){
        for(int j=i; j>=h  && data[j] < data[j-h]; j--){
          swap(data,j,j-h);
        }
      }
      h = h / 3;
    }
  }

  private void swap(int[] data, int i, int j){
    int buff = data[i];
    data[i] = data[j];
    data[j] = buff;
  }
}
