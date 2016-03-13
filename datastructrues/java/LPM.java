
public class LPM {

  String[] suffixArray;

  public LPM(String data){
    this.suffixArray = new String[data.length];
    for(int i=0; i<data.length; i++){
      this.suffixArray[i] = data.subString(i);
    }
    Arrays.sort(suffixArray); //use MSD QuickSort
  }

  public String lcp(){
    String lcp; int maxLen=0;
    for(int i=1; i<suffixArray.length; i++){
      String prefix = prefix(suffixArray[i],suffixArray[i-1]);
      if(prefix.length() > maxLen) lcp = prefix;
    }
    return lcp;
  }

  private String prefix(String a, String b){
    int len = Math.min(a.length, b.length);
    int i=0;
    for(i=0; i<len && a.charAt(i) == b.charAt(i); i++){}
    return a.subString(0,i);
  }

  public Iterable<String> contextSearch(String data){
    Queue<String> queue = new Queue<String>();
    int lo = 0, hi = data.length-1;
    while(lo <= hi){
      int mid = (lo+hi) / 2;
      String val = suffixArray[mid];
      int cmp = compare(data,val);
      if(cmp > 0) lo = mid+1;
      else if (cmp < 0) hi = mid-1;
      else {
        queue.enque(suffixArray[mid]);
       for(int i=mid-1; i>0 && compare(data,suffixArray[i]) == 0; i--){
         queue.enque(suffixArray[i]);
       }
       for(int i=mid+1; i<data.length && compare(data,suffixArray[i]) == 0; i++){
         queue.enque(suffixArray[i]);
       }
      }
    }

  }

}
