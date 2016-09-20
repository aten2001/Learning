
import java.util.Scanner;

public class KthZero {


    static class Node{
        public int val;
        public int prev;
        public int next;
        
        public Node(int val){
            this.val = val;
            this.prev = -1;
            this.next = -1;
        }
    }
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        Node [] data  = new Node[N];
        int prev = -1;
        int first = -1;
        for(int i=0; i<N; i++){
            data[i] = new Node(sc.nextInt());
            if(data[i].val == 0){
                if(first == -1){
                    first = i;
                    prev = i;
                }else{
                    Node prevNode = data[prev];
                    prevNode.next = i;
                    data[i].prev = prev;
                    prev = i;
                }
            }
        }

        while(M-- > 0){
            int cmd = sc.nextInt();
            if (cmd == 1) {
                int k = sc.nextInt();
                String output = getIndex(k,first,data);
                System.out.println(output);
            }else{
                int p = sc.nextInt();
                int x = sc.nextInt();
                Node extVal = data[p];
                if(x == 0 & extVal.val != 0){
                    first = addToIndex(data,p,first);
                }else if(x != 0 && extVal.val == 0){
                    first = removeIndex(data,p,first);
                }
                extVal.val = x;
               
            }

        }


    }
    private static int removeIndex(Node[] data, int p, int first) {
        Node n = data[p];
        
        if(p == first){
            first = n.next;
            if(n.next != -1){
                Node nxt = data[n.next];
                nxt.prev = -1;
            }
            n.next = -1;
        }else{
            Node prev = data[n.prev];
            Node nxt = data[n.next];
            prev.next = n.next;
            nxt.prev = n.prev;
            n.next = -1;
            n.prev = -1;
        }
        return first;
    }
    
    private static int  addToIndex(Node[] data, int p, int first) {
        Node n = data[p];
        if(first < 0){
            first = p;
        }else if (p < first) {
            Node f = data[first];
            f.prev = p;
            n.next = first;
            first = p;
        }else{
            for(int i=p-1; i>=first; i--){
                if (data[i].next != -1){
                    int next = data[i].next;
                    data[i].next = p;
                    n.prev = i;
                    Node nx = data[next];
                    nx.prev = p;
                    n.next= next;
                    break;
                }
            }
        }
        return first;
        
    }
    private static String getIndex(int k, int first, Node[] data) {
        if(first == -1) return "NO";
        if (k == 1) return first+"";
        Node n = data[first];
        String output = "NO";
        for (int i=2; i<k; i++){
            if(n.next == -1) break;
            n = data[n.next];
        }
        return (n.next == -1)? output : ""+n.next;
    }
}

