

public class TST {


    Node root;


    public void put(String val){

    }

    public List<String> (String prefix){
        List<String> lst = new ArrayList<>();
        Node node = get(0,root,prefix);
        if(node == null) return lst;
        else return getWords(root,prefix,lst);
    }

    private List<String>  getWords(Node node, String prefix, List<String> lst){
        if(node == null) return lst;
        if(node.word) lst.add(prefix);

        List<String> vals = getWords(

    }

    private Node get(int i, Node node, String val){
        if(i == val.length()) return null;
        if(node == null) return null;

        char ch = val.charAt(i);
        if(ch < node.ch) return get(i,node.left,val);
        if(ch > node.ch) return get(i,node.right,val);
        else {
            if(i == val.length()-1) return node;
            else return get(i+1,node.mid,val);
        }
    }

}
