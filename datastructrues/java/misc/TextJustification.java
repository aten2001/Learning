
import java.util.*;

public class TextJustification {

    public static void main(String[] args){
    
      Scanner sc = new Scanner(System.in);
      int N = 10;
      String text = sc.nextLine();
      String[] words = text.split(" ");
      int [] nextPtr = new int[words.length];
      recBuild(words,nextPtr,0,words.length);
      int i=0;
      while (i < nextPtr.length){
          int next = nextPtr[i];
          System.out.println(buildString(i,next,words));
          i = next;
      }
    }

    private static String buildString(int start, int end, String[] words){
        String output = "";
        for(int i = start; i<end; i++){
            if (i != start) output += " ";
            output += words[i];
        }
        return output;
    }

    private static long recBuild(String[] words,
            int[] nextPtr, int start, int end){
        int st = end-1;
        long badVal = badness(words,start,end-1);
        for(int i=end-2; i>start; i--){
            long val = badness(words,start,i) + recBuild(words,nextPtr,i+1,end);
            if(val < 0 || val == Long.MAX_VALUE) continue; // < 0 important to catch roll over to negative
            if(val < badVal){
                badVal = val;
                st = i;
            }
        }
        nextPtr[start] = st+1;
        return badVal;
    }

    private static long badness(String[] words, int start, int end){
        String concat = "";
        for(int i=start; i<=end; i++){
            if (i != start) concat += " ";
            concat += words[i];
        }
        if(concat.length() > 10) return Long.MAX_VALUE;
        else return (long) Math.pow(Math.abs(concat.length() - 10),3);
    }
}
