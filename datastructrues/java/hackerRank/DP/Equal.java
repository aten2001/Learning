import java.util.*;


public class Equal {


    static class Node {
        public final int[] data;
        public int incr;
        public int selected;
        public int count;
        public boolean equal;

        public Node(int[] data, int incr, int selected){
            this.data = data;
            this.incr = incr;
            this.selected = selected;
            int check = -1;
            boolean equal = true;
            for(int i=0; i<this.data.length; i++){
                int val = this.data[i];
                if (check == -1) check = val;
                else if(check != val) equal = false;
            }
            this.equal = equal;
            this.count = 0;
        }

        public Node(int[] data, boolean equal, int count){
            this.data = data;
            this.count = count;
            this.equal = equal;
        }


        public Node apply(){
            int[] newData = new int[data.length];
            int check = -1;
            boolean equal = true;
            for(int i=0; i<this.data.length; i++){
                int val;
                if(i == this.selected) val = this.data[i];
                else val = this.data[i] + this.incr;

                if (check == -1) check = val;
                else if(check != val) equal = false;

                newData[i] = val;
            }

            return new Node(newData,equal,this.count+1);
        }

        public Node cloneWitIncr(int incr){
            Node newNode = new Node(this.data,this.equal,this.count);
            newNode.selected = this.selected;
            newNode.incr= incr;
            return newNode;
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        while(T-- > 0){
            int N = sc.nextInt();
            int[] data = new int[N];
            for(int i=0; i<N; i++) data[i] = sc.nextInt();
            List<Node> lst = new ArrayList<>(N*N);
            for(int i=0; i<N; i++){
               lst.add(new Node(data,1,i));
               lst.add(new Node(data,2,i));
               lst.add(new Node(data,5,i));
            }
            int minOps = recSearch(lst);
            System.out.println(minOps);
        }

    }

    private static int recSearch(List<Node> lst){
        while(!lst.isEmpty()){
            Node val = lst.remove(0);
            if (val.equal) return val.count;    // used in first loop
            Node newVal = val.apply();
            if(newVal.equal) return newVal.count;
            for(int i=0; i<val.data.length; i++){
                newVal.selected = i;
                lst.add(newVal.cloneWitIncr(1));
                lst.add(newVal.cloneWitIncr(3));
                lst.add(newVal.cloneWitIncr(5));
            }
        }
        return -1;
    }

}
