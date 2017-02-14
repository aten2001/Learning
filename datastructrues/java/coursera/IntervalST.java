

public class IntervalST<Key extends Comparable<Key>,Value> {

  static class Node<Key, Value>{
    Key lo, hi, maxHi;
    Value val;
    Node left, right;

  }

  private Node root;

  public void put(Key lo, Key hi, Value v){
    root = recPut(root, lo, hi,v);
  }

  public Node recPut(Node n, Key lo, Key hi, Value v){
    if(n == null) return new Node(lo,hi,v);
    int cmp = lo.compareTo(n.lo);
    if(lo < 0) n.left = recPut(n.left,lo,hi,v);
    else if (cmp > 0) n.right = recPut(n.right,lo,hi,v);
    else n.value = v;

    n.maxHi = max(n.left,n.right);
    return n;
  }

  private int maxHi(Node a, Node b){
    int aHi = (a == null) ? 0 : a.maxHi;
    int bHi = (b == null) ? 0 : b.maxHi;
    return (aHi > bHi) ? aHi : bHi;
  }

  public Value get(Key lo, Key hi){
    Node n = recGet(root,lo,hi);
    return n.value;
  }

  private Node recGet(Node n, Key lo, Key hi){
    if( n == null && n.intersects(lo,hi) ) return n;
    else if(n.left == null) return recGet(n.right,lo,hi);
    else if(n.left.maxHi < lo) return recGet(n.right,lo,hi);
    else return recGet(n.left,lo,hi);
  }

  private boolean intersects(Key lo, Key hi){
    return(n.lo >=lo && n.lo < hi 
        || n.hi >lo && n.hi <= hi)
  }

  public void delete(Key lo, Key hi){

  }

  Iterable<Value> intersects(Key lo, Key hi){
    Node n = root;
    Queue<Value> output = new Queue<Value>();
    while(n !=null){
      if(n.intersects(lo,hi)) output.enque(n.value);
      if(n.left == null) n = n.right;
      else if(n.left.maxHi < o) n = n.right;
      else n = n.left;
    }

    return output;
  }


}
