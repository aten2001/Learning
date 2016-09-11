
public class LinkedQueue {

  static class Node {
    T data;
    Node next, prev;

    public Node(T data){
      this.data = data;
    }
  }

  private Node head, tail;
  private int N;


  public void enque(T val){
    Node n = new Node(val);
    n.next = head;
    head.prev=n;
    head = n;
    if(tail == null) tail = head;
    N++;
  }

  public T deque(){
    if(tail == null) throw new EmptyStackException();
    T val = tail.data;
    Node pre = tail.prev;
    tail.prev = null;
    tail = pre;
    if(tail == null) head = null;
    N--;
  }

}
