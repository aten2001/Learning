// https://www.hackerrank.com/contests/world-codesprint-9/challenges/box-operations


import java.util.*;

public class BoxOperations {


    static class Node {
        Node left;
        Node right;
        int l,r,sum,min;

        public Node(int l, int r){
            this.l = l;
            this.r = r;
        }

        public Node(int l, int r, int val){
            this.l = l;
            this.r = r;
            this.sum = val;
            this.min = val;
        }

        public boolean isLeaf(){
            return this.l == this.r;
        }

        public void add(int c){
            this.sum += c;
            this.min = this.sum;
        }

        public void divideAndFloor(int c){
           this.sum  = Math.floorDiv(this.sum,c);
           this.min = this.sum;
        }

        public void update(){
            this.sum = left.sum + right.sum;
            this.min = Math.min(left.min, right.min);
        }

        public String toString(){
            if(this.l == this.r){
                return this.sum +"" ;
            }else {
                return this.left.toString()+" "+this.right.toString();
            }
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int q = sc.nextInt();
        int [] box = new int[n];
        for(int i=0; i<n; i++){
            box[i] = sc.nextInt();
        }
        Node root = buildRoot(box,0,n-1);
        //System.out.println(root);
        for(int i=0; i<q; i++){
            int cmd = sc.nextInt();
            int l = sc.nextInt();
            int r = sc.nextInt();
            if (cmd == 1){
                int c = sc.nextInt();
                root = recUpdate(root,l,r,c,1);
            }else if(cmd == 2){
                int d = sc.nextInt();
                root = recUpdate(root,l,r,d,2);
            }else if(cmd == 3){
                int min = getVal(root,l,r,1);
                System.out.println(min);
            }else{
                int sum = getVal(root,l,r,2);
                System.out.println(sum);
            }
         //   System.out.println(root);
        }
    }

    private static int getVal(Node node, int l, int r, int op){
        if(node.isLeaf()){
            if (l <= node.l && node.r <= r){
                return node.min; // min/sum are the same at leaf
            }else{
                return (op == 1)? Integer.MAX_VALUE : 0;
            }
        }else{
            //retur if range == search range
            if(node.l == l && node.r == r) return (op == 1)?  node.min : node.sum;
            int output = (op == 1) ?  Integer.MAX_VALUE : 0;
            //avoid going till leaf if the range fits is search range
            if(l<= node.l && node.r <= r) {
                if(op == 1){
                    output = Math.min(output,node.min);
                }else{
                    output += node.sum;
                }
            }else{
                if(l <= node.left.r) {
                    if(op == 1){
                        output = Math.min(getVal(node.left,l,r,op),output);
                    }else{
                        output += getVal(node.left,l,r,op);
                    }
                }
                if(r >= node.right.l){
                    if(op == 1){
                        output = Math.min(getVal(node.right,l,r,op),output);
                    }else{
                        output += getVal(node.right,l,r,op);
                    }
                }
            }
            return output;
        }
    }

    private static Node recUpdate(Node node, int l, int r, int val, int op){
        if(node.isLeaf()){
            if (l <= node.l && node.r <= r){
                if(op == 1){
                    node.add(val);
                }else{
                    node.divideAndFloor(val);
                }
            }
            return node;
        }
        if(l <= node.left.r) node.left = recUpdate(node.left,l,r,val,op);
        if(r >= node.right.l) node.right = recUpdate(node.right,l,r,val,op);

        node.update();
        return node;
    }

    private static Node buildRoot(int[] box,int lo, int hi){
        if( hi == lo) return new Node(lo,hi,box[lo]);
        int mid = (lo + hi) / 2;
        Node parent = new Node(lo,hi);
        parent.left = buildRoot(box,lo,mid);
        parent.right = buildRoot(box,mid+1,hi);
        return parent;
    }

}
