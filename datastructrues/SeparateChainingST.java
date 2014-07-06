

public class SeparateChainingST {

  private int M = 97;
  private Node [] nodes = new Node[M];

  private int hash(Key k){
    return k.hashCode() % M;
  }

  public Value get(Key k){
    int i = hash(k);
    for(Node n = nodes[i]; n != null; n=n.next){
      if(n.key.equals(k)) return n.value;
    }
    return null;
  }

  public void delete(Key k){
    int i = hash(k);
    nodes[i]=recDelete(nodes[i],k);
  }

  private Node recDelete(Node n , Key k){
    if(n == null) return null;
    if(n.key.equals(k)) return n.next;
    else return recDelete(n.next,k);
  }

  public void put(Key k,Value val){
    int i = hash(k);
    for(Node n = nodes[i]; n!=null; n=n.next){
      if(n.key.equals(k)){
        n.value = val;
        return;
      }
    }
    nodes[i] = new Node(k,val,nodes[i]);
  }

  static class Node{
    Key k;
    Value val;
    Node next;

    public Node(Key k, Value val, Node old){
      this.k = k;
      this.val = val
      this.next = old;
    }
  }
}
