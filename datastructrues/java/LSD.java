
public class LSD {

  public static void sort (String[] data, int W){
    int R = 256;
    int[] count = new int[R+1];

    for(int j=W-1; j>=0; j--){

      for(int i=0; i<data.length; i++){
        count[data[i].charAt(j)+1]++;
      }

      for(int i=1; i<=R; i++){
        count[i] += count[i-1];
      }

      for(int i=0; i<data.length; i++){
        aux[count[data[i].charAt(j)]++] = data[i];  
      }
    }
  }


  public static void sort(int[] data){
    int R = 256;
    int[] count = new int[R+1];

    for(int j=3; j>=0; j--){

      for(int i=0; i<data.length; i++){
        count[getByte(data[i],j)+1]++;
      }

      for(int i=1; i<=R; i++){
        count[i] += count[i-1];
      }

      for(int i=0; i<data.length; i++){
        aux[count[getByte(data[i],j)]] = data[i];
      }

      for(int i=0; i<data.length; i++){
        data[i] = aux[i];
      }
    }
  }

  private int getByte(int val, int i){
    if(i == 2) val = val >>> 8;
    else if(i == 1) val = val >>> 16;
    else if(i == 0) val = val >>> 24;
    return 0x000000FF & val;
  }

}
