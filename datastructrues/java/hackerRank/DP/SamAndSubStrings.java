
import java.util.*;

public class SamAndSubStrings{


    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String val = sc.nextLine();
        long output = 0L;
        for(int i=0; i<val.length(); i++){
            for(int j=i+1; j<val.length()+1; j++){
                output += Long.parseLong(val.substring(i,j));
            }
        }
        System.out.println(output % 1000000007);
    }
}
