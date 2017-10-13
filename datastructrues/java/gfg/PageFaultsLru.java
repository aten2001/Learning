import java.util.*;
//http://practice.geeksforgeeks.org/problems/page-faults-in-lru/0
public class PageFaultsLru {

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while(T-- > 0){
            int N = sc.nextInt();
            int[] actions = new int[N];
            for(int i=0; i<N; i++) actions[i] = sc.nextInt();
            int C = sc.nextInt();

            PageMonitor monitor = new PageMonitor(C);

            for(int i=0; i<N; i++){
                monitor.add(actions[i]);
            }

            System.out.println(monitor.pageFaults());
        }
    }
    static class Node{
        int val;
        Node next;
        Node prev;

        public Node(int v){
            this.val = v;
        }

    }

    static class PageMonitor {
        Node head;
        Node tail;
        Map<Integer,Node> map;
        int counter;
        int size;
        int faults;

        public PageMonitor(int C){
            this.size = C;
            this.counter = 0;
            this.map = new HashMap<>();
            this.faults = 0;
        }

        void decoupleNode(Node n){
            Node prev = n.prev;
            Node next = n.next;
            if(prev != null) prev.next = next;
            if(next != null) next.prev = prev;
            n.next = null;
            n.prev = null;
        }

        void makeHead(Node n){
            n.next = head;
            head.prev = n;
            n.prev = null;
            head = n;
        }

        public void add(int v){
            if(this.map.containsKey(v)){
                Node n = this.map.get(v);
                if(head == n) return;
                if(tail == n) tail = n.prev;
                decoupleNode(n);
                makeHead(n);
            }else{
                if(counter < size){ //simple add
                    Node n = new Node(v);
                    if(counter == 0){
                       tail = n;
                       head = n;
                    }else{
                        makeHead(n);
                    }
                    counter++;
                    this.map.put(v,n);
                }else{ // memory filled up and needs rejection
                    Node n = new Node(v);
                    makeHead(n);
                    moveTailUp();
                    this.map.put(v,n);
                }
                this.faults++;
            }

        }

        void moveTailUp(){
            Node n = tail;
            tail = n.prev;
            tail.next = null;
            n.prev = null;
            if(this.map.containsKey(n.val)) this.map.remove(n.val);
        }



        public int pageFaults(){
            return this.faults;
        }

    }
}
