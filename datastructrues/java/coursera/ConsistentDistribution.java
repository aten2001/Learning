
import java.util.Arrays;
import java.util.Scanner;

public class ConsistentDistribution {
    
    static class PerDataHeap {
        public PerDataHeap(int m) {
            this.data = new long[m+1];
            this.pq = new int[m+1];
            for(int i=0; i<m+1; i++){
                this.data[i] = 0l;
                this.pq[i] = i;
            }
            this.maxT = 0l;
        }
        private long [] data;
        private int[] pq;
        private long maxT;
        
        public void updateMin(long l) {
            int index = this.pq[1];
            this.data[index] +=l;
            if (maxT < data[index]) maxT = data[index];
            sink(1);        
        }
        
        void swap(int a, int b){
            int buff = this.pq[a];
            this.pq[a] = this.pq[b];
            this.pq[b] = buff;
        }
        
        void sink(int a){
            int twice = 2*a;
            if(twice < pq.length){
                int cmp = twice;
                if(twice+1 < pq.length && less(twice+1,twice)){
                    cmp = twice+1;
                }
                if(less(cmp,a)){
                    swap(a,cmp);
                    sink(cmp);
                }
            }
        }
        
        boolean less(int a, int b){
            return this.data[this.pq[a]] < this.data[this.pq[b]];
        }
        
        public long getMaxT() {
            return maxT;
        }
        
    }

    public static void main(String[] args){
        
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        long[] probs = new long[N];
        PerDataHeap perDay = new PerDataHeap(M);
        int j = 0;
        while(j < N){
            probs[j++] = sc.nextLong();
        }
        Arrays.sort(probs); //sort ascending
        for(int i=N-1; i>= 0; i--){
            perDay.updateMin(probs[i]);
        }
        System.out.println(perDay.getMaxT());
        sc.close();
        
    }
}

