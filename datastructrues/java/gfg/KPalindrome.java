import java.util.*;
public class KPalindrome {

    public static void main(String[] args){
        boolean output = is_k_palin("acdcb",1);
        System.out.println(output);
    }


    static boolean is_k_palin(String str, int k)
    {
        System.out.println(str);
        if(str.length()==1) return true;
        if(str.charAt(0) == str.charAt(str.length()-1)) return is_k_palin(str.substring(1,str.length()-1),k);
        if(k > 0){
            boolean outcome =  is_k_palin(str.substring(0,str.length()-1),k-1) || is_k_palin(str.substring(1,str.length()),k-1);
            return outcome;
        }else{
            return false;
        }
    }

}
