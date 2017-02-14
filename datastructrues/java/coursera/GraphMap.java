
public class GraphMap {

  private int E;
  private Map<Integer,Bag<Integer>> vertices;

  public GraphMap(){
    this.E = 0;
    this.vertices = new HahsMap<Integer,Bag<Integer>>();
  }

  public Iterable<Integer> adj(int v){
    if(vertices.containsKey(v)){
      return vertices.get(v);
    }
    return Collections.EMPTY_LIST;
  }

  public void addEdge(int a, int b){
    if(!vertices.containsKey(a)) vertices.put(a,new Bag<Integer>());
    if(!vertices.containsKey(b)) vertices.put(b,new Bag<Integer>());
    vertices.get(a).add(b);
    vertices.get(b).add(a);
  }

