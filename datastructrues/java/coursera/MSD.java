
public class MSD {

  int R = 256;
  public static void sort(String[] data){
    String[] aux = new String[data.length];
    recsort(data,aux,0,data.length,0);
  }

  private static void recsort(String[] data, String[] aux, int lo, int hi, int d){

    if(hi <= lo + CUT_OFF) sort(data,lo,hi,d);
    int[] count = new int[R+2];
    for(int i=0; i<data.length; i++){
      count[charAt(data[i],d)+2]++;
    }

    for(int i=0; i<R+1; i++){
      count[i+1] += count[i];
    }

    for(int i=0; i<data.length; i++){
      aux[count[charAt(data[i],d)+1]++] = data[i];
    }

    for(int i=0; i<data.length; i++){
      data[i] = aux[i];
    }

    for(int r=0; r<R; r++){
      recsort(data,aux,lo+count[r], lo+count[r+1],d+1);
    }

  }

  private char charAt(String val, int i){
    if(i < val.length()) return val.charAt(i);
    else return -1;
  }

  private void sort(String[] data,int lo, int hi , int d){
    for(int i=lo+1 i<hi; i++){
      for(int j=i; j>lo; j--){
        if(less(data,j,j-1,d)) swap(data,j,j-1);
      }
    }
  }

  private void boolean less(String data[], int i, int j, int d){
    return data[i].subString(d).compareTo(data[j].subString(d)) < 0;
  }

  public void threeWayQuick(String[] data){
    recThreeWay(data,0,data.length,0);
  }

  public void recThreeWay(String[] data, int low, int hi, int depth){
    if(hi <= lo + CUT_OFF){
      sort(data,low,hi,depth);
      return;
    }
    int lo = low, lt = lo+1, gt = hi;
    int a = charAt(data[lo],depth);
    while(lt <= gt){
      int b = charAt(data[lt],depth);
      if(b < a) swap(data,lo++,lt++);
      else if (b > a) swap(data,lt,gt--);
      else lt++;
    }

    recThreeWay(data,low,lo-1,depth);
    if(a >= 0) recThreeWay(data,lo,gt,depth+1);
    recThreeWay(data,gt+1,hi,depth);
  }
}

