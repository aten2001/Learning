
public class RWayTrie<T> {

  private static int R = 256;//ASCI CHARS

  static class Node {
    Object val;
    Node [] pointer;
    int count;
    public Node(){
      this.pointer = new Node[R];
      this.count=0;
    }

    public boolean noChildreen(){
      return this.count == 0;
    }
  }

  private Node root;


  public T get(String key){
    Node node = get(root,key,0);
    if(node == null) return null;
    else return (T)node.val;
  }

  private Node get(Node node, String k, int d){
    if(node == null) return null;
    if(d == k.length()) return node;
    return get(node.pointer[k.charAt(d)],k,d+1);
  }


  public void put(String key, Object val){
    root = put(root,key,val,0);
  }

  private Node put(Node node, String key, T val, int i){
    if(node == null) node = new Node();
    if(i = key.length()) node.val = val;
    else {
      if(node.pointer[key.charAt(i)] == null) node.count++;
      node.pointer[key.charAt(i)] = put(node.pointer[key.charAt(i)],key,val,i+1);
    }
    return node;
  }

  public void delete(String key){
    root = delete(root,key,0);
  }

  private Node delete(Node node, String key, int i){
   if(node == null) return null;
   if(i == key.length()) node.val = null;
   else {
     node.pointer[key.charAt(i)] = delete(node.pointer[key.charAt(i)],key,i+1);
     if (node.pointer[key.charAt(i)] == null) node.count++;
   }
   if(noChildOrVal(node)) return null; 
   else return node;
  }

  private noChildOrVal(Node node){
    if(node == null) return true;
    else return (node.val == null && node.noChildreen());
  }

  public Iterable<String>  prefixSearch(String pref){
    Queue<String> prefixes  = new Queue<String>();
    Node n  = get(root,pref,0)
    if(n.value != null) prefixes.enque(perf);
    populate(n,perfixes,pref);
  }

  public void populate(Node n , Queue<String> output, String pref){
    for(Node child : n.pointer){
      if(child == null) continue;
      if(child.val != null) output.enque(pref+child.ch);
      populate(n.child,ouptut,pref+child.ch);
    }
  }


}

