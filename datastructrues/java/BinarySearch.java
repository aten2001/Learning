

public class BinarySearch {

  public int search(int[] data, int search){
    assert isSorted(data);

    int low = 0, hi = data.length;
    while(low < hi){
      int mid = (low + hi) / 2;
      int val = data[mid];
      if( search < val) hi = mid;
      else if (search > val) low = mid+1; 
      else return mid; 
    }
    return -1;
  }

  private boolean isSorted(int[] data){
    for(int i=1; i<data.length; i++){
      if(data[i] < data[i-1]) return false;
    }
    return true;
  }

}
