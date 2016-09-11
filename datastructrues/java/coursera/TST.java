
public class TST {

  static class Node {
    Character ch;
    Node left, center, right;
    Object val;
  }

  private Node root;

  public T get(String key){
    Node node = get(root,key,0);
    return (node == null) ? null : (T)node.val;
  }

  private Node get(Node node, String key , int i){
    if(node == null || key.length() == i) return node;
    char c = key.charAt(i);
    if(c < node.ch) return get(node.left,key,i);
    else if(c > node.ch) return get(node.right,key,i);
    else return get(node.center,key,i+1);
  }

  public void put(String key, T val){
    root = put(root,key,val,0);
  }

  private Node put(Node node, String key, T val, int i){
    if(node == null) node = new Node();
    if(key.length() == i) node.val = val;
    else {
      char c = key.charAt(i);
      if(node.ch == null) node.ch =c;

      if(c < node.ch) node.left = put(node.left,key,val,i);
      else if (c > node.ch) node.right = put(node.right,key,val,i);
      else node.center = put(node.center,key,val,i+1);
    }
    return node;
  }

  public void delete(String key){
    root =  delete(root,key,0);
  }

  private Node delete(Node node, String key, int i){
    if(node == null) return null;
    if( i == key.length()) node.val = null;
    else {
      char c = key.charAt(key);
      if(c < node.ch) node.left = delete(node.left,key,i);
      else if(c > node.ch) node.right = delete(node.right,key,i);
      else node.center = delete(node.center,key,i+1);
    }

    return compressed(node); //reduce trie structure length
  }

  private Node min(Node n){
    if(n == null) return null;
    Node p = n;
    while(p.left != null){
      p = p.left;
    }
    return p;
  }

  private Node delMin(Node n){
    if(n == null) return null;
    if(n.left == null) return n.right;
    n.left = delMin(n.left);
    return n;
  }


  private Node compressed(Node node){
    if(node == null) return null;
    if(node.val == null && node.center == null){
      if(node.left == null && node.right == null) return null;

      if(node.left ==null) node = node.right;
      else if(node.right == null) node = node.left;
      else { // simiar to hibard deletion
        Node min  = min(node.right);
        node.right = delMin(node.right);
        min.right = node.right;
        min.left = node.left;
        node = min;
      }
      return node;
    }
    return node;
  }

  public Iterable<String> pefixKeys(String prefix){
    Node n = get(root,prefix,0);
    Queue<String> keys = new Queue<String>();
    if(n != null && n.val != null) keys.enque(prefix);
    collect(n.center,prefix,keys);
    return keys;
  }

  private void collect(Node n, String buff, Queue<String> output){
    if(n == null) return;
    if(n.val != null) output.enque(buff+n.ch);
    collect(n.left, prefix, output);
    collect(n.center, prefix+n.ch, output);
    collect(n.right, prefix, output);
  }

  public String longestPrefixMatch(String key){
    return lcp(root,key,"","",0);
  }

  private String lcp(Node n,String key,String buff, String prefix, int i){
    if(n == null || i == key.length()) return prefix;
    char c = key.charAt(i);
    if(c < n.ch) return lcp(n.left,key,buff,prefix,i);
    else if( c > n.ch) return lcp(n.right,key,buff,prefix,i);
    else{
      if(n.val != null) prefix = buff+n.ch;
      return lcp(n.center,key,buff+ch,perfix,i+1);
    }
  }



}
