
public class PersistantBST<Key,Value> {

  private static final int N = 10; // number of versions
  class Node {
    Key key;
    Value[]; //value per version
    Value value;
    Node left, right, old;
    int count;
    int version;

    public Node(Key k, Value val){
      this.key = k;
      this.value = (Value[]) new Object[N];
      this.version=0;
      this.value[this.version]=val;
      this.count=1;
    }

    public Node newVersionCopy(Value val){
      Node newNode = new Node(this.key,val);
      newNode.version = this.version+1;
      newNode.old = this;
    }

    public Value  getVal(){
      return this.value[this.version % this.value.length];
    }

    public void setVal(Value val){
      this.value[++this.version % this.value.length];
    }

    public boolean isFull(){
      return this.version+1 < this.value.length;
    }

  }

  private Node root;

  public Value get(Key k, int version){
    Node n = root;
    while(n != null){
      int cmp = k.compareTo(n.key);
      if(cmp < 0) n = n.left;
      else if (cmp > 0) n = n.right;
      else return n.getVal();
    }
    return null;
  }


  public void put(Key k, Value val,int version){
    root = recPut(root,k,val);
  }

  private void recPut(Node n, Key k, Value val){
    if(n == null) return new Node(k, val);
    int cmp = k.compareTo(n.key);
    if(cmp < 0) n.left = recPut(n.left, k ,val);
    else if (cmp > 0) n.right = recPut(n.right, k, val);
    else {
      if(n.isFull(){
        n = n.newVersionCopy(val);
      }else{
        n.setVal(val);
      }
    }
    n.count = 1 + sizeOf(n.left) + sizeOf(n.right);
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

  private void inorder(Node n, Key lo, Key hi, Queue<Key> queue){
    if(n == null) return;
    if(lo.compareTo(n.key) < 0)inorder(n.left,queue);
    if(lo.compareTo(n.key) < 0 && hi.compareTo(n.key) > 0) queue.enque(n.value);
    if(hi.compareTo(n.key) > 0) inorder(n.right,queue);
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
    }
    n.count = 1 + sizeOf(n.left) + sizeOf(n.right);
    return n;
  }


}



