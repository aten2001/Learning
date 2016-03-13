
import java.util.Map;
import java.util.HashMap;

public class HuffMan {

  static class Node implements Comparable<Node> {
    final Character ch; //used only for leaf nodes
    int freq; //used only for compares in building up the trie
    private final Node left,right;

    public Node(Character c, int f, Node l, Node r){
      this.ch = c;
      this.freq = f;
      this.left = l;
      this.right = r;
    }

    public boolean isLeaf(){
      return (this.left == null && this.right == null);
    }

    public int compareTo(Node that){
      return this.freq - that.freq;
    }

  }


  public Node buildTrie(Reader reader){

    Map<Character,Node> map = new HashMap<Character,Node>(256);
    while(reader.hasNext()){
      char c = reader.read();
      Node n = map.get(c);
      if(n == null) n = new Node(c,1,null,null);
      else n.freq++;
      map.put(c,n);
    }
    MinPQ<Node> pq = new MinPQ<>(map.size());

    for(Character k : map.getKeys()) pq.insert(map.get(k));

    while(pq.size() > 1){
      Node x = pq.delMin();
      Node y = pq.delMin();
      Node parent = new Node(null,x.freq+y.freq,x,y);
      pq.insert(parent);
    }
    return pq.delMin():
  }

  public Map<Character,String> buildCodeMap(Node trie){
    Map<Character,String> output = new HashMap<>();
    inorder(trie,output,'');
    return output;
  }

  private void inorder(Node trie, Map<Character,String> output, String code){
    if(trie == null) return;
    inorder(trie.left,output,code+"0");
    if(trie.ch != null) output.put(code);
    inorder(trie.right,output,code+"1");
  }


  public void writeTrie(Node root, Writer wr){

  }

  public void compress(Node trie, Reader r, Writer w){

    Map<Character,String> codeMap = buildCodeMap(trie);
    while(r.hasNext()){
      String code = codeMap.get(r.next());
      w.write(convertToByteChars(code));
    }
  }



}


