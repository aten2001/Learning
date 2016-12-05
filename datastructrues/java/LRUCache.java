import java.util.*;
import java.lang.*;

public class LRUCache<K,V> {

  class Node {
    public K key;
    public V val;
    public Node next;
    public Node prev;

    public boolean isHead(){
      return this.prev == null;
    }

    public boolean isTail(){
      return this.next == null;
    }

    public void remove(){
      Node prev = this.prev;
      Node next = this.next;
      if(null != next) next.prev = prev;
      if(null != prev) prev.next = next;
      this.prev = null;
      this.next = null;
    }

    public void addOnTopOf(Node n){
      this.next = n;
      n.prev = this;
    }

  }

  Node head;
  Node tail;
  final Map<K,Node> map;
  final int th;
  int sz;

  public LRUCache(int sz){
    this.th = sz;
    this.sz = 0;
    map = new HashMap<>();
  }

  public void put(K key, V val){
    if(map.containsKey(key)){ // if key is present
      Node node = map.get(key);
      node.val = val;
      if(!node.isHead()) {
        node.remove();
        node.addOnTopOf(head);
        head = node;
      }
    }else{

      if(sz == th){ //remove tail
        Node node = tail;
        tail = node.prev;
        node.remove();
        map.remove(node.key);
        sz--;
      }

      Node node = new Node();
      node.val = val;
      node.key = key;
      if(head == null){
        head = node;
        tail = node;
      }else{
        node.addOnTopOf(head);
        head = node;
      }
      map.put(key,node);
      sz++;
    }
  }


  public V get(K key){
    V val = null;
    if(map.containsKey(key)){
      Node node = map.get(key);
      val = node.val;
      node.remove();
      node.addOnTopOf(head);
      head = node;
    }
    return val;
  }

  public String toString(){
    StringBuilder builder = new StringBuilder();
    builder.append(head.key);
    Node n = head;
    while(n.next != null){
      builder.append(" -> ");
      n = n.next;
      builder.append(n.key);
    }
    return builder.toString();
  }

  public static void main(String[] args){
     LRUCache<Integer,String> cache = new LRUCache<>(3);
     cache.put(1,"1");
     cache.put(2,"2");
     cache.put(3,"3");
     cache.put(4,"4");
     System.out.println(cache.toString());
     System.out.println(cache.get(1));
     System.out.println(cache.get(2));
     System.out.println(cache.toString());
     cache.put(5,"5");
     System.out.println(cache.toString());
     System.out.println(cache.get(3));
     System.out.println(cache.get(2));
     System.out.println(cache.toString());
  }

}
