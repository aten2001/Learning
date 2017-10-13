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

            LinkedHashSet<Integer> set = new LinkedHashSet<>();

            int faults = 0;
            for(int i=0; i<N; i++){
                int val = actions[i];
                if(set.contains(val)){
                    set.remove(val);
                    set.add(val);
                }else{
                    if(i<C) {
                        faults++;
                        set.add(actions[i]);
                    }
                }
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

        public void add(int v){
            if(this.map.containsKey(v)){
                Node n = this.map.get(v);
                if(n == head) return;
                if(n == tail)tail = n.prev;
                Node prev = n.prev;
                Node next = n.next;
                prev.next = next;
                if(next != null) next.prev = prev;
                n.next = head;
                head.prev = n;
                head = n;
            }else{
                if(counter < size){ //simple add
                    Node n = new Node(v);
                    if(counter == 0){
                       tail = n;
                    }else{
                        n.next = head;
                        head.prev = n;
                    }
                    head = n;
                    counter++;
                    this.map.put(v,n);
                }else{ // memory filled up and needs rejection
//                    System.out.println(v+" "+tail.val+" "+tail.prev.val);
                    Node n = new Node(v);
                    n.next = head;
                    head.prev = n;
                    this.map.remove(tail.val);
                    Node prev = tail.prev;
                    tail.prev = null;
                    prev.next = null;
                    tail = prev;
                    this.map.put(v,n);
                }
                this.faults++;
            }

        }

        public int pageFaults(){
            return this.faults;
        }

    }
}
