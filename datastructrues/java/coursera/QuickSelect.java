
public class QuickSelect {

  private boolean exist(int[] data, int k){
    new Shuffle.random(data);
    int lo =0, hi = data.length-1;
    while(lo < hi){
      int val = data[lo];
      int mid = partion(data,lo,hi);
      if(k < val) lo= mid+1;
      else if(k > val) hi = mid-1;
      else return true;
    }
    return false;
  }

  private int partion(int[]data, int lo, int hi){
    int i=lo, j=hi+1;
    int val = data[lo];

    while(true){
      while(data[++i] < val){};
      if(i > hi) break;

      while(data[--j] > val) {};

      if(i >= j) break;
      else swap(data,i,j);
    }
    swap(data,lo,j);
    return j;
  }

  private int[] partion3Way(int[] data, int lo, int hi){
    int i=lo+1, lt=lo, gt=hi;
    int val = data[lo];
    while(i <= gt){
      if( data[i] < val) swap(data,lt++,i++);
      else if( data[i] > val) swap(data,i,gt--);
      else i++;
    }
    return { lt, gt }
  }

  private int select(int[] data, int val){
    int lo = 0, hi=data.length-1;
    while(lo < hi){
      int [] mids = partion3Way(data,lo,hi);
      if(val <  mids[0]) hi = mids[0]-1;
      else if(val > mids[1]) lo = mids[1]+1;
      else return mids[1];
    }
  }



