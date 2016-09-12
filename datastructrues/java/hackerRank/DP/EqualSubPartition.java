
import java.util.*;
import java.math.*;


public class EqualSubPartition {

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while(T-- > 0){
            int N = sc.nextInt();
            int[] data = new int[N];
            for(int i=0; i<N; i++) data[i] = sc.nextInt();
            Arrays.sort(data); //sorts array
            int[] aux = new int[data.length];
            for(int i=0,j=N-1; i<N; i++,j--) aux[i] = data[j];
            int score = computeScore(0,aux,data,0,N-1,true);
            System.out.println(score);
        }
    }

    static int computeScore(int sum, int[]data, int[] aux, int start, int end,boolean desc){
        int size = end - start + 1;
        if (size <= 1) return sum;
        int lo =start; int hi = end;
        long lsum = 0L; long hsum = 0L;
        List<Integer> indicies = getIndicies(start,end,desc);
        System.out.println(Arrays.toString(data));
        for (int a=0; a<indicies.size(); a++){
            int i = indicies.get(a);
            //first indicies always goes to first parition - helps when all are 0
            if(a == 0){
                aux[lo++] = data[i];
                lsum += data[i];
                continue;
            }
            //go to low parition only if lsum < hsum
            if (lsum < hsum){
                aux[lo++] = data[i];
                lsum += data[i];
            }else {
                aux[hi--] = data[i];
                hsum += data[i];
            }
            System.out.println(lsum +"_"+hsum);
        }
        if (hsum != lsum){
            return sum;
        }else {
            return Math.max(computeScore(sum+1,aux,data,start,lo-1,true), computeScore(sum+1,aux,data,lo,end,false));
        }
    }

    //get indicies based on order
    private static List<Integer> getIndicies(int start, int end, boolean desc){
        List<Integer> output = new ArrayList<>(end-start+1);
        if(desc){
            for(int i=start; i<=end; i++){
                output.add(i);
            }
        }else{
            for(int i=end; i>=start; i--){
                output.add(i);
            }
        }
        return output;
    }


}
