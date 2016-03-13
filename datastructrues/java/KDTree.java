

/**
 * Used to shuffle between multiple dimensions
 */

interface Dimension {
  int noOfDimensions();
  Comparable getDimensionVal(int indx);
}

/**
 * Works well well Key across dimenstions are distinct
 * Does not work when comparision could be such that Key is same in one dimension and distinc in another.
 */

public class KDTree<Key extends Dimension, Value> {

  static class Node<Key extends Dimenstion,Value>{
    Key key;
    Value val;
    Node left, right;

    public Node(Key k, Value val){
      this.key = k;
      this.val = val;
    }

  }

  private Node root;
  private int N;


  public Value get(Key k){
    if(root == null) return null;
    Node n = root;
    int N = 0;
    int D = n.k.getNoOfDimenstions();
    while(n != null){
      Comparable ck = k.gteCompareAttr(N % D);
      Comparable c = n.k.getCompareAttr(N % D);
      int cmp = ck.compareTo(c);
      if(cmp < 0) n = n.left;
      else if(cmp > 0) n = n.right;
      else return n.val;
    }
  }


  public void put(Key k, Value val){

  }

