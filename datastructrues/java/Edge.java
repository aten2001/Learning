
public class Edge implements Comparable<Edge>{

  private int v,w;
  private double wt;

  public Edge(int v, int w, double wt){
    this.v=v;
    this.w=w;
    this.wt=wt;
  }

  public int either(){
    return this.v;
  }

  public int other(int a){
    return (this.v == a) ? this.w : this.v;
  }

  public double weight(){
    return this.wt;
  }

  public int compareTo(Edge other){
    if(this.wt > other.wt) return 1;
    if(this.wt < other.wt) return -1;
    return 0;
  }
}
