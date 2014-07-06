
public class KeyIndexCounting {

  public KeyIndexCounting(char[] data){

    int[] count = new int[R+1];

    //build index counts
    for(int i=0; i<data.lenght; i++){
      count[data[i]+1]++;
    }

    // build cumilates
    for(int i=1; i<count.length; i++){
      count[i] += count[i-1];
    }

    // copy data into aux according to position
    for(int i=0; i<data.length; i++){
      aux[count[data[i]]++]= data[i];
    }

    //copy back to data
    for(int i=0; i<data.length; i++){
      data[i] = aux[i];
    }
  }

}
