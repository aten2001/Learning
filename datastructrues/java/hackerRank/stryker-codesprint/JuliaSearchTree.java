
import java.util.Scanner;

public class JuliaSearchTree {

    
    static class Node{
        
        public Node(int nextInt) {
            this.val = nextInt;
        }
        public Node left;
        public Node right;
        public int val;
    }
    
    static class BTree{
        Node root;
        int[] heightCount;
        
        public BTree(int N){
            this.heightCount = new int[N]; //max possible in case of 2 nodes
        }

        public void add(int nextInt) {
            this. root = recAdd(root, nextInt, 0);
            
        }

        private Node recAdd(Node node, int nextInt, int ht) {
            if(node == null){
                this.heightCount[ht]++;
                return new Node(nextInt);
            }
            if(nextInt < node.val)       node.left =  recAdd(node.left,nextInt,ht+1);
            else if(nextInt > node.val)  node.right =  recAdd(node.right,nextInt,ht+1);
            else if(nextInt == node.val) node.val = nextInt;
            return node;
        }

        public int count() {
            int val = 0;
            for(int i=0; i<this.heightCount.length; i++){
                val += i*this.heightCount[i];
            }
            return val;
        }
        
    }
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        BTree tree = new BTree(N);
        while(N-- > 0){
            tree.add(sc.nextInt());
        }
        System.out.println(tree.count());
    }
}

