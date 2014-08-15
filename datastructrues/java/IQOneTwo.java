import java.util.Map;
import java.util.HashMap;

public class IQOneTwo {

  public String longestSub(String data, int N){
    int index =0, size=0;
    Map<Character,Boolean> icount= new HashMap<>();
    Map<Character,Boolean> jcount= new HashMap<>();

    for(int i=0, j=0, lo=0; i<data.length(); i++){
      if(icount.size() == N && !icount.containsKey(data.charAt(i))){ // check size case
        while(jcount.size() == 1 && jcount.containsKey(data.charAt(j))){ // incrment j till it meets next distinct char
          j++;
        }
        int sz = i - lo;
        if(size < sz){
          size = sz;
          index=lo;
          lo=j;
          icount.remove(data.charAt(lo));
          jcount.remove(data.charAt(lo));
        }
      }
      if(jcount.size() < 1){
        jcount.put(data.charAt(j++),true);
      }else if(jcount.size() == 1 && jcount.containsKey(data.charAt(j))){
        j++;
      }
      icount.put(data.charAt(i),true);
    }

    return data.substring(index,index+size);
  }

  
  public static void main(String[] args){
    IQOneTwo obj = new IQOneTwo();
    System.out.println(obj.longestSub("aaabbbbcdaabsbsssssssdddssss",2));
  }
}

