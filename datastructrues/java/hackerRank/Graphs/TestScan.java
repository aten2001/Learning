import java.util.*;
import java.io.*;

public class TestScan {



    public static int parseInt( final String s )
    {
        // Check for a sign.
        int num  = 0;
        int sign = -1;
        final int len  = s.length( );
        final char ch  = s.charAt( 0 );
        if ( ch == '-' )
            sign = 1;
        else
            num = '0' - ch;

        // Build the number.
        int i = 1;
        while ( i < len )
            num = num*10 + '0' - s.charAt( i++ );

        return sign * num;
    } 

    public static void main(String[] args) throws IOException {
        try(BufferedReader reader = new  BufferedReader(new InputStreamReader(System.in))){
            String[] line = reader.readLine().split("\\s");
            int T = Integer.parseInt(line[0]);
            while(T-- > 0){
                line = reader.readLine().split("\\s");
                int N = Integer.parseInt(line[0]);
                int M = Integer.parseInt(line[1]);
                Set<Integer>[] adj = new HashSet[N];
                for(int i=0; i<N; i++) adj[i] = new HashSet(N);
                while(M-- > 0){
                    String buff = reader.readLine();

                    int a = buff.indexOf(' ');
                    int b = buff.indexOf(' ',a+1);
                    //int a = -1, b= -1;
                    //for(int i=0; i< buff.length(); i++){
                    //    if (' ' == buff.charAt(i)){
                    //        if (a == -1) a = i;
                    //        else b = i;
                    //    }
                    //}
                    //line = reader.readLine().split("\\s");
                    int v = parseInt(buff.substring(0,a));
                    int w = parseInt(buff.substring(a+1,b));
                    int wt =parseInt(buff.substring(b+1,buff.length()));
                    adj[v-1].add(w);
                    adj[w-1].add(v);
                }
                line = reader.readLine().split("\\s");
                int source = Integer.parseInt(line[0]);
            }
        }
    }

}
