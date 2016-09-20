import java.util.*;

public class HiddenMessage {
    
    static class WordOffset {
        public WordOffset(int i, int j) {
            this.start = i;
            this.end = j;
        }
        public int start = -1;
        public int end = -1;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String t = sc.nextLine();
        String[] prefixArray = new String[t.length()];
        for(int i=0; i<t.length(); i++){
            prefixArray[i] = t.substring(i, t.length());
        }
        
        String[] words = sc.nextLine().split(" ");
        WordOffset[] offset = new WordOffset[words.length];
        int wordIndex = 0;
        for(int i=0; i< prefixArray.length && wordIndex < words.length; i++){
            String prefix = prefixArray[i];
            String word = words[wordIndex];
            if(prefix.length() < word.length()) break; //cant match
            if(prefix.substring(0, word.length()).equals(word)){
                offset[wordIndex++] = new WordOffset(i,i+word.length()-1);
                i = i-1; //continue same prefix for next substring
            }
        }
        
        boolean outputFlag = (wordIndex == words.length);
        String secondLine = "0";
        if(wordIndex != 0){
            StringBuilder builder = new StringBuilder();
            for(int i=0; i<wordIndex; i++){
                if(i != 0) builder.append(" "); //start space
                String word = words[i];
                WordOffset wf = offset[i];
                builder.append(word).append(" ");
                builder.append(wf.start).append(" ");
                builder.append(wf.end);
            }
            secondLine = builder.toString();
        }
        int thirdLine = 0;
        if(outputFlag){
            int [] usedCount = new int[t.length()];
            for(int i=0; i<wordIndex; i++){
                WordOffset wf = offset[i];
                for(int j=wf.start; j<= wf.end; j++){
                    usedCount[j]++;
                }
            }
            int delCount = 0;
            int insertCount = 0;
            int spaceCount = words.length -1;
            for(int i=0; i<usedCount.length; i++){
                if(usedCount[i] == 0) delCount++;
                if(usedCount[i] > 1)  insertCount += usedCount[i]-1;
            }
            thirdLine += insertCount + delCount + spaceCount;
        }
        
        System.out.println((outputFlag) ? "YES" : "NO");
        System.out.println(secondLine);
        System.out.println(thirdLine);
        
        sc.close();
    }

}

