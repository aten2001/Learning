
import java.util.*;
import java.lang.*;

public class MaxSubArray {


    static interface MaxCounter {
        void count(int val);
        long maxSum();
    }

    static class NonContiguousCounter implements MaxCounter{

        long output = Long.MIN_VALUE;

        public void count(int val){
            if(output < 0){
                if (val > output){
                    output = val;
                }
            }else{
                if (val > 0){
                    output += val;
                }
            }
        }

        public long maxSum(){
            return output;
        }
    }

    static class ContiguousCounter implements MaxCounter{

        long output = Long.MIN_VALUE;
        Long buffer = null;
        long secondaryOutput = 0;

        public void count(int val){
            if(output < 0){
                if (val > output){
                    output = val;
                }
            }else{
                if (buffer == null){
                    if (val > 0){
                        output += val;
                    }else{
                        buffer = output + val;
                    }
                }else if (buffer > 0){
                    if (val > 0){
                        output = buffer + val;
                        buffer = null;
                    }else{
                        buffer += val;
                    }
                }else{ //buffer <= 0
                    if (val > 0){
                        secondaryOutput += val;
                        if (secondaryOutput > output){
                            output = secondaryOutput;
                            secondaryOutput = 0;
                            buffer = null;
                        }
                    }else{
                        //ignore when val < 0 and buffer is <0
                    }
                }
            }
        }

        public long maxSum(){
            return output;
        }
    }

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0){
            int N = sc.nextInt();
            MaxCounter nonContiguous = new NonContiguousCounter();
            MaxCounter contiguous = new ContiguousCounter();
            long buff = 0;
            while(N-- > 0){
                int val = sc.nextInt();
                nonContiguous.count(val);
                contiguous.count(val);
            }
            System.out.println(contiguous.maxSum()+" "+nonContiguous.maxSum());
        }
    }
}
