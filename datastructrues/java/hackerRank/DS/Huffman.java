
import java.util.*;

public class Huffman {

    public static void main(String[] args){
        Scanner sc = new Scanner();
        while(true){
            String val = sc.nextLine();
            Node root = encode(val);
            System.out.println(root.toString());
        }
    }


    private static Node encode(String val){
        List<Obj> charSortedByFred;

        Node root = null;
        for(!charSortedByFreq.isEmpty()){
            if(root == null){
                Obj obj1 = charSortedByFred.poll();
                Obj obj2 = charSortedByFred.poll();
                Node node1 = new Node();
                Node node2 = new Node();
                node1.ch = obj1.ch;
                node1.freq = obj1.freq;
                node2.ch = obj2.ch;
                node2.freq = obj2.freq;
                Node node3 = new Node();
                node3.left = node1;
                node3.right = node2;
                node3.feq = node1.freq + node2.freq;
                root = node3
            }else{
                Obj obj = charSortedByFreq.poll();
                if(root.freq <= obj.freq){

                }else{
                    Obj obj = charSortedByFreq.poll();

                }




            
        }

    }
