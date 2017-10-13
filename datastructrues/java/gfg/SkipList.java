
public class SkipList {

    private Node {
        int val;
        Node left;
        Node right;
        Node top;
        Node bottom;
        int type; // 0 = head, 1 = tail, or others
        int steps;
        public Node(int type){
            this.type = type;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public SkipList (){
        head = new Node(0);
        tail = new Node(1);
        head.right = tail;
        tail.left = head;
        size = 0;
    }


    Node find(int v){
        if(size == 0) return null;
        Node n =  recFind(head,v,0);
        return n;
    }

    Node recFind(Node n, int v,int steps){
        if(n.isGreater(v)){
            Node former = n.left;
            if(former.down != null) return recFind(former.down,v,steps);
            else{
                former.steps = steps;
                return former;
            }
        }
        else if(n.lesser(v)) return recFind(n.right,v,steps+1);
        else {
            n.steps = steps;
            return n;
        }
    }

    public void insert(int val){
        Node n = find(val);
        if(n.val != val){
            size++;
            Node i = simpleInsert(n,val);
            int ht = computeRandomHt();
            for(int i=0; i<ht; i++){
                n = oneLevelUp(n);
                Node ni = simpleInsert(n,val);
                ni.down = i;
                i.up = ni;
            }
        }
    }

    Node simpleInsert(Node prev, int val){
        Node n = new Node(2);
        n.left = prev;
        n.right = prev.right;
        prev.right.left = n;
        perv.right = n;
        n.steps = prev.steps;
        return n;
    }

    public void contains(int val){
        Node n = find(val);
        return (n.val == val);
    }

    public Iterable<int> list(){

    }

}
