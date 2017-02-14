
public class LinkedStack<T> {

  static class Node {
    T val;
    Node pointer;

    public Node(T val){
      this.val = val;
    }
  }

  public LinkedStack(){
    this.N = 0;
  }

  private Node root;
  private int N;

  public void push(T data){
    Node node = new Node(data);
    node.pointer = root;
    root = node;
    N++;
  }

  public T pop(){
    if(root == null) throw new RunTimeException("Empty Stack");
    Node n = root;
    root = root.pointer;
    N--;
    return n.val;
  }

}
