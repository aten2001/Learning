import java.util.*;

public class DecodeString {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while(T-- > 0){
            String s = sc.next();
            Map<Integer,Integer> bracesMap = buildMap(s);
            String o = decode(0,s.length(),s,bracesMap);
            System.out.println(o);
        }
    }

    static Map<Integer,Integer> buildMap(String input){
        Map<Integer,Integer> map = new HashMap<>();
        Stack<Integer> st = new Stack<>();
        for(int i=0; i<input.length(); i++){
            if(input.charAt(i) == '[') st.push(i);
            else if(input.charAt(i) == ']'){
                int s =st.pop();
                map.put(s,i);
            }
        }
        return map;
    }

    static String decode(int s, int e, String input,Map<Integer,Integer> bracesMap){
        StringBuilder output = new StringBuilder();
        int number=0;
        for(int i=s; i<e; i++){
            char ch = input.charAt(i);
            if(isAlpha(ch)){
                output.append(ch);
                number = 0;
            }else if(isNumber(ch)){
                number = (number *10) + Character.getNumericValue(ch);
            }else if(ch == '['){
                //System.out.println(i);
                //System.out.println(bracesMap);
                String val = decode(i+1,bracesMap.get(i),input,bracesMap);
                for(int j=0; j<number;j++) output.append(val);
                number = 0;
                i = bracesMap.get(i);
            }
        }
        return output.toString();
    }

    private static boolean isAlpha(char ch){
        return ((ch >= 'a' && ch <= 'z')
                || ( ch >= 'A' && ch <= 'Z' ));
    }

    private static boolean isNumber(char ch){
        return (ch >= '0' && ch <= '9');
    }

}
