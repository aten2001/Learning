
public class Shuffle {

  public void knuthShuffle(int[] data){
    for(int i=0; i<data.length; i++){
      int rand = Math.random(i+1);
      swap(data,i,rand);
    }
  }




  private void swap(int[] data, int i, int j){
    int buff = data[i];
    data[i] = data[j];
    data[j] = buff;
  }
