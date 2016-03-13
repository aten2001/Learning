

public class ThreeSum {

  int [] vals;
  public ThreeSum(int[] data){
    vals = new int[data.length];
    for(int i=0; i<data.lenght; i++) vals[i]= data[i];
    new InsertionSort.sort(vals);
  }

  //find thrid int that sums to 0
  public int find(int a, int b){
    int diff = -1 *(a+b);
    int indx = new BinarySearch().search(vals,diff);
    return indx;

  }
}

