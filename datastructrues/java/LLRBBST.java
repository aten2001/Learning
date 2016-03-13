


public class LLRBBST<Key,Value> {

  private static boolean RED = true;
  private static boolean BLACK = false;

  class Node {
    Key key;
    Value value;
    Node left, right;
    int count;
    boolean color;

    public Node(Key k, Value val){
      this.key = k;
      this.value = val;
      this.count=1
      this.color=RED;
    }


  }

  private Node root;

  private boolean isRed(Node n){
    return (n == null) ? false : n.color;
  }

  private Node rotateLeft(Node n){
    Node x = n.right;
    n.right = x.left;
    x.left = n;
    n.count = 1 + sizeOf(n.left) + sizeOf(n.right);
    x.count = 1 + sizeOf(x.left) + sizeOf(x.right);
    x.color = n.color;
    n.color = RED;
    return x;
  }

  private Node rotateRight(Node n){
    Node x = n.left;
    n.left = x.right;
    x.right = n;
    n.count = 1 + sizeOf(n.left) + sizeOf(n.right);
    x.count = 1 + sizeOf(x.left) + sizeOf(x.right);
    x.color = n.color;
    n.color = RED;
    return x;
  }

  private Node flipColor(Node n){
    n.left.color=BLACK;
    n.right.color=BLACK;
    n.color = RED;
  }


  public Value get(Key k){
    Node n = root;
    while(n != null){
      int cmp = k.compareTo(n.key);
      if(cmp < 0) n = n.left;
      else if (cmp > 0) n = n.right;
      else return n.value;
    }
    return null;
  }


  public void put(Key k, Value val){
    root = recPut(root,k,val);
  }

  private void recPut(Node n, Key k, Value val){
    if(n == null) return new Node(k, val);
    int cmp = k.compareTo(n.key);
    if(cmp < 0) n.left = recPut(n.left, k ,val);
    else if (cmp > 0) n.right = recPut(n.right, k, val);
    else n.value = val;
    n.count = 1 + sizeOf(n.left) + sizeOf(n.right);
    if(isRed(n.right) && !isRed(n.left)) n = rotateLeft(n);
    if(isRed(n.left) && isRed(n.left.left)) n = rotateRight(n);
    if(isRed(n.left) && isRed(n.right)) n = flipColors(n);
    return n;
  }

  private int sizeOf(Node n){
    if (n == null) return 0;
    else return n.count;
  }

  private int rank(Key k){
    Node n = root;
    return recRank(n,k);
  }

  private int recRank(Node n, Key k){
    if(n == null) return 0;
    int cmp = k.compare(n.key);
    if(cmp < 0) return recRank(n.left,k);
    else if (cmp >0) return 1 + sizeOf(n.left)+ recRank(n.right,k);
    else if(cmp == 0) return 1 + sizeOf(n.left);
  }

  public Iterable<Key> keys() {
    Queue<Key> queue = new Queue<Key>():
    inorder(root,queue);
    return queue;
  }

  public Iterable<Key> keys(Key lo, Key hi) {
    Queue<Key> queue = new Queue<Key>():
    inorder(root,queue,lo,hi);
    return queue;
  }

  private void inorder(Node n, Queue<Key> queue){
    if(n == null) return;
    inorder(n.left,queue);
    queue.enque(n.value);
    inorder(n.right,queue);
  }

  public int rangeCount(Key lo, Key hi){
    if(get(lo) != null) return 1 + rank(hi) - rank(lo);
    else return rank(hi) - rank(lo);
  }


  private void inorder(Node n, Key lo, Key hi, Queue<Key> queue){
    if(n == null) return;
    int cmplo = lo.compareTo(n.key);
    int cmphi = hi.compareTo(n.key);
    if(cmplo < 0) inorder(n.left,lo,hi,queue);
    if(cmplo <=0 && cmphi >=0) queue.enque(n.key);
    if(cmphi > 0) inorder(n.right,lo,hi,queue);
  }

  public Key min(){
    if(root == null) return null;
    Node n = root;
    while(n.left != null){
      n = n.left;
    }
    return n.key;
  }

  public Key max(){
    if(root == null) return null;
    Node n = root;
    while(n.right != null){
      n = n.right;
    }
    return n.key;
  }


  public Key floor(Key k){
    if(root == null) return null;
    Node n = root;
    Key floor = null;
    while(n != null){
      int cmp = k.compareTo(n.key);
      if(cmp < 0) n = n.left;
      else if( cmp > 0){
       floor = n.key;
       n = n.right;
      }else{
        floor = n.key;
        break;
      }
    }
    return floor;
  }

  public Key ceiling(Key k){
    if(root == null) return null;
    Node n = root;
    Key ceil = null;
    while(n!= null){
      int cmp = k.compareto(n.key);
      if(cmp < 0){
        ceil = n.key;
        n = n.left;
      }
      else if(cmp > 0) n = n.right;
      else {
        ceil = n.key;
        break;
      }
    }
  }

  public void deleteMin(){
    if(root == null) throw new RunTimeException("Empty bst");
    root = delMin(root);
  }

  private Node delMin(Node n){
   if(n.left == null) return n.right;
   n.left = delMin(n.left);
   n.count = 1 + sizeOf(n.left) + sizeOf(n.right);
   return n;
  }

  public void deleteMax(){
    if(root == null) throw new RunTimeException("Empty BST");
    root = delMax(root);
  }

  private Node delMax(Node n){
    if(n.right == null) return n.left;
    n.right = delMax(n.right);
    n.count = 1 + sizeOf(n.left) + sizeOf(n.right);
    return n;
  }

  public void delete(Key k){
    root = recDelete(root,k);
  }

  private Node recDelete(Node n,Key k){
    if(n == null) return;
    int cmp = k.compareTo(n.key);
    if(cmp < 0) n = recDelete(n.left,k);
    else if(cmp > 0) n = recDelete(n.right,k);
    else{
      if(n.right == null) return n.left;

      Node m = min(n.right);
      n.right = delMin(n.right);
      n.key = m.key;
      n.value = m.value;
      n.color = m.color
    }
    n.count = 1 + sizeOf(n.left) + sizeOf(n.right);
    if(isRed(n.right) && !isRed(n.left)) rotateLeft(n);
    if(isRed(n.left) && isRed(n.left.left)) rotateRight(n);
    if(isRed(n.left) && isRed(n.right)) flipColors(n);
    return n;
  }


}



