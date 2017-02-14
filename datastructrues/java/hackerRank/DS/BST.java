
public class BST{


    static class Node {
        Object data;
        Node left;
        Node right;
    }


    Node root;

    public void put(Object val){
        root = recPut(root,val);
    }

    private Node recPut(Node node,Object val){
        if(node == null) return new Node(val);
        if(val < node.data) node.left = recPut(node.left,val);
        else if(val > node.data) node.right = recPut(node.right,val);
        else node.data = val;
        return  node;
    }


    public Object get(Object val){
        Node node = recGet(root, val);
        return node == null? null : node.data;
    }

    private Node recGet(Node node, Object val){
        if(node == null) return null;
        if(val < node.data) return recGet(node.left,val);
        else if(val > node.data) return recGet(node.right,val);
        else return node;
    }


}
