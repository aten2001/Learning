import java.util.*;

//http://practice.geeksforgeeks.org/problems/total-decoding-messages/0

public class DecodingMsg {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while(T-- > 0){
            int N = sc.nextInt();
            String val = sc.next();
            try{
                int output = decode(val);
                System.out.println(output);
            }catch(IllegalStateException e){
                System.out.println(0);
            }
        }
    }

    static int decode(String val){
        if(val.length() == 1) return 1;       // return count
        if(val.charAt(0) == '0') return decode(val.substring(1)); // ignore 0 as starting char
        if(invalid(val.substring(0,2))) throw new IllegalStateException("invalid 0"); //case like 30, 50 etc..
        if(valid(val.substring(0,2))){ // check for valid 2 digit string
            if(val.length() == 2) return 1+ decode(val.substring(1)); // if valid and last
            else return decode(val.substring(1)) + decode(val.substring(2)); // if valid and not last
        }else{
            return decode(val.substring(1)); // if not valid
        }
    }

    static boolean invalid(String val){
        if(val.charAt(1) == '0'){
            int intVal = Integer.parseInt(val);
            return intVal > 26;
        }
        return false;
    }

    static boolean valid(String val){
        int intVal = Integer.parseInt(val);
        return intVal < 27;
    }

}
