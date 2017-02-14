
import java.util.*;

public class  NoPrefixSet {


    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        TST tst = new TST();
        int N = sc.nextInt();
        String badSet = null;
        while(N-- > 0){
            String val = sc.next();
            if(badSet != null) continue;
            boolean isPrefix = tst.checkAndPut(val);
            if(isPrefix){
                badSet = val;
            }
        }
        if(null != badSet){
            System.out.println("BAD SET");
            System.out.println(badSet);
        }else{
            System.out.println("GOOD SET");
        }

    }

    static class PrefixException extends Exception{

    }

    static class TST {

        static class Node {
            char ch;
            Node right;
            Node mid;
            Node left;
            boolean word;


            Node (char ch){
                this.ch = ch;
            }
        }

        int _newNodeCount = 0;
        Node root;

        public boolean checkAndPut(String val) {
            try{
                root = recPut(0,root,val);
                if(this._newNodeCount == 0) throw new PrefixException(); // no new node implies this i path of existing with this word prefix
                return false;
            }catch(PrefixException p){
                return true;
            }finally{
                this._newNodeCount=0;
            }
        }


        private Node recPut(int i, Node node, String val)throws PrefixException{
            char ch = val.charAt(i);
            if(node == null){
                node = new Node(ch);
                this._newNodeCount++;
            }


            if(ch < node.ch) node.left = recPut(i, node.left, val);
            else if(ch > node.ch) node.right = recPut(i, node.right, val);
            else {
                //System.out.println(node.word+"_"+node.ch);
                if(node.word) throw new PrefixException(); // already word exist
                if (i == val.length()-1) node.word = true;
                else node.mid = recPut(i+1, node.mid, val);
            }

            return node;

        }
    }
}
