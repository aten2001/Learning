
import java.util.ArrayList;
public class SkipList<T extends Comparable<T> {

  static class Node {
    ArrayList<Node> fwdPointers
    ArrayList<Node> reversePointers;;
    T val;

    public Node(T val){
      this.val = val;
      fwdPointers = new ArrayList<>();
      reversePointers = new ArrayList<>();
    }
  }

  public SkipList(){
    this.N = 0;
  }

  private Node first;
  private int N;

  public void set(T data){
    root = recSet(root,data);
    N++;
  }

  public Iterable<T> iterator(){
    return null;
  }

  public boolean contains(T data){
    return false;
  }

  public T getByPos(int pos){
    Node node = first;
    int size = N;
    while( null != node ){
      if (pos > (size/2)){
         node = node.fwPointers.
      }
    }

    return null;
  }

}
