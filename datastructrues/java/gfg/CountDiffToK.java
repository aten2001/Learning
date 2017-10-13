import java.util.*;

public class CountDifToK {

    public static void main(String[] args){
        int data[]= {1, 5, 3, 4, 2};
        int k = 3;

        Arrays.sort(data);
        Set<Tupple> output = new HashSet<>();

        for(int i=0; i<data.length; i++){
            int j = search(data,i+1,data.length,k+data[i]);
            if(j != -1) {
                output.add(new Tupple(data[i],data[j]));
            }
        }

        for(Tupple t : output){
            System.out.println(t);
        }
    }

    private int search(int[] data, int lo, int hi, int key){
        if(lo < hi) return -1;
        int mid = (lo + hi)/2;
        if(data[mid] < key) return search(data,mid+1,hi);
        else if(data[mid] > key) return search(data,lo, mid-1);
        else return mid;
    }

    static class Tupple {
        int i,j;

        public Tupple(int i, int j){
            this.i = i;
            this.j = j;
        }

        @Overide
        public int hashcode(){
            return 39*i + 39*j;
        }

        @Override
        public boolean equals(Object obj){
            if(obj == null) return false;
            if(obj instanceof Tupple){
                Tupple other = (Tupple) obj;
                if(this.i == other.i && this.j == other.j) return true;
                if(this.i == other.j && this.j == other .i) return true;
            }
            return false;
        }

        @Override
        public String toString(){
            return "{"+this.i+", "+this.j+"}";
        }
    }

}
