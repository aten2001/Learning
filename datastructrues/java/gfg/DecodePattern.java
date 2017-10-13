import java.util.*;

public class DecodePattern {


    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        String[] cache = new String[21];
        cache[1] = "1";
        while(T-- > 0){
            int N = sc.nextInt();
            String patt = findPatt(N,cache);
            System.out.println(patt);
        }
    }

    static String findPatt(int N , String[] cache){
        if (cache[N] != null) return cache[N];
        String preVal = findPatt(N-1, cache);
        char old = preVal.charAt(0);
        int count = 1;
        StringBuilder output = new StringBuilder();
        for(int i=1; i<preVal.length(); i++){
            if(preVal.charAt(i) == old){
                count++;
            }else{
                output.append(count)
                    .append(old);
                count = 1;
                old = preVal.charAt(i);
            }
        }
        output.append(count).append(old);
        cache[N] = output.toString();
        return cache[N];
    }

}



