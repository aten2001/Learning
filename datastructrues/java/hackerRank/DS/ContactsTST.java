
import java.util.*;

public class ContactsTST {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        TST tst = new TST();
        int N  = sc.nextInt();
        while(N-- > 0){
            String cmd = sc.next();
            String val = sc.next();
            if("add".equals(cmd)){
                tst.add(val);
            }else{
                int count = tst.searchCount(val);
                System.out.println(count);
            }
        }
    }

    private static class TST {

        static class Node {
            char ch;
            Node left;
            Node mid;
            Node right;
            boolean word;
            int count;

            Node(char ch){
                this.ch = ch;
            }
        }

        Node root;

        public void add(String val){
            root = recPut(root,val,0);
        }

        private Node recPut(Node node, String val, int i){
            if(val.length() == i) return node;
            char ch = val.charAt(i);
            if(node == null){
                node = new Node(ch);
            }

            if(ch < node.ch) node.left = recPut(node.left,val,i);
            else if(ch > node.ch) node.right = recPut(node.right,val,i);
            else if(i == val.length()-1) node.word = true;
            else  node.mid = recPut(node.mid,val,i+1);

            return node;
        }

        public int searchCount(String prefix){
            Node node = get(0,root,prefix);
            if(node == null) return 0;
            else{
                int count = 0;
                if(node.word) count +=1;
                return count + countWords(node.mid);
            }
        }

        private int countWords(Node node){
            int count = 0;
            if(node == null) return count;
            if(node.word) count = count+1;
            count += countWords(node.mid);
            count += countWords(node.left);
            count += countWords(node.right);
            return count;
        }

        private Node get(int i, Node node, String word){
            if(i == word.length()) return node;
            if(node == null) return null;
            char ch = word.charAt(i);

            if(ch < node.ch) return get(i, node.left, word);
            else if (ch > node.ch) return get(i, node.right, word);
            else if (i == word.length()-1) return node;
            else return get(i+1,node.mid,word);
        }
    }

}
