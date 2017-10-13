import java.util.*;
//http://practice.geeksforgeeks.org/problems/find-median-in-a-stream/0
// much more elegant solution using PQ http://ide.geeksforgeeks.org/qagv4T

public class MedianStream {


    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        SkipList lst = new SkipList(N);
        while(N-- > 0){
            lst.add(sc.nextInt());
            System.out.println(lst.median());
        }
    }

    static class SkipList {

        static class Node {
            int val;
            Node left, right, up, down;

            Node(int val){
                this.val = val;
            }
        }

        Node head;
        Node tail;
        Node med;
        int size;
        int maxHt;


        public SkipList(int N) {
            head = new Node(Integer.MIN_VALUE);
            tail = new Node(Integer.MAX_VALUE);
            head.right = tail;
            tail.left = head;
            med = head;
            this.size = 0;
            this.maxHt = (int) Math.log(N);
            updateHeadAndTail(head,tail,maxHt);
        }

        private void updateHeadAndTail(Node h, Node t, int ht){
            while(ht-- > 0){
                Node uh = new Node(h.val);
                Node ut = new Node(t.val);
                uh.down = h;
                h.up = uh;
                ut.down = t;
                t.up = ut;
                uh.right = ut;
                ut.left = uh;
                h = uh;
                t = ut;
            }
        }


        public void add(int v){
            Node prev = find(head,v);
            Node i = insert(prev,v);
            int ht = computeRandomHt();
            for(int j=0; j<ht; j++){
                prev = findLevelUp(prev);
                Node ni = insert(prev,v);
                ni.down = i;
                i.up = ni;
            }
            if(size % 2 == 0) {
                if( med.val <= v) med = med.right;
            }else{
                if( med.val > v) med = med.left;
            }
            size++;
        }


        Node find(Node n, int v){
            if(n.val > v){
                if(n.left.down != null) return find(n.left.down,v);
                else return n.left;
            }
            else return find(n.right,v);
        }

        Node insert(Node prev, int v){
            Node n = new Node(v);
            n.left = prev;
            n.right = prev.right;
            prev.right.left = n;
            prev.right = n;
            return n;
        }

        Node findLevelUp(Node prev){
            if(prev.up != null) return prev.up;
            else return findLevelUp(prev.left);
        }

        int computeRandomHt(){
            return (int) (Math.random()*(maxHt*1.0));
        }

        public int median(){
            if(size %2 == 0){
                return (med.val+ med.right.val)/2;
            }else {
                return med.val;
            }
        }

    }
}
