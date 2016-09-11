
import java.util.ArrayList;
import java.util.HashMap;

public class IQOne {

  public void subString(String data, int N){
    int [] skips = new int[data.length()];
    int[] index = new int[data.length()];
    StringBuilder build = new StringBuilder();
    int counter=0;
    Character buff = null;

    for(int i=0,j=0; i<data.length(); i++){
      char ch  = data.charAt(i);
      if(buff != null && buff != ch){
        counter++;
        index[++j]=i;
        build.append(buff);
      }
      skips[j]++;
      buff = ch;
    }


    int sz=0;
    int indx = index[0];
    for(int i=0; i<N; i++){
      sz +=skips[i];
    }

    for(int i=N, buffSz=sz; i<counter; i++){
      buffSz = buffSz - skips[i-N] + skips[i];
      if(buffSz > sz){
        sz= buffSz;
        indx = index[i-N+1];
      }
    }
    System.out.println(data.substring(indx,indx+sz));
  }

  public static void main(String[] args){
    new IQOne().subString("aaabbbbcdaabsbsssssssdddssss",2);
  }

}
