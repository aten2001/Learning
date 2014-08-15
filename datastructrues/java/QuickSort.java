

public class QuickSort {

  private static final CUT_OFF = 10;

  public void sort(int[] data){
   new Shuffle().knutShuffle(data);
   recSort(data,0,data.length-1);
  }

  public void sort3Way(int[] data){
    int mid = turkeys9(int[]data);
    rec3Way(data,0,data.length-1);
  }

  private int turkeys9(int[] data){
    return 0;
  }

 public void recSort(int[] data, int lo, int hi){
  if(hi - lo <= CUT_OFF) Insertion.sort(data,lo.hi);
  int mid = partion(data,lo,hi);
  recSort(data,lo, mid-1);
  recSort(data,mid+1,hi);
 }

 public void rec3Way(int[] data, int lo, int hi){
  if(hi - lo <= CUT_OFF) Insertion.sort(data,lo.hi);
  int [] mids = partion(data,lo,hi);
  recSort(data,lo, mids[0]-1);
  recSort(data,mid+1,mids[1]+1);
 }

 private int partion(int[] data, int lo, int hi){
   int i=lo, j=hi+1;
   int val = data[lo];
   while(true){

     while(data[++i] < val){}
     if(i > hi) break;

     while(data[--j] > val){}

     if(i >= j) break;
     else swap(data,i,j);

   }
   swap(data,lo,j);
   return j;
 }

 public int[] pation3way(int[] data, int lo, int hi){
   int i=lo+1, gt=hi, lt=lo;

   while(i <=  gt){
     if(data[i] < data[lt]) swap(data,i++,lt++);
     else if(data[i] > data[lt]) swap(data,i,gt--);
     else i++;
   }

   return { lt, gt };
 }

}
