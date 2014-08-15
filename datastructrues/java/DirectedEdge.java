
public class DirectedEdge {

  private final int from, to;
  private final double wt;

  public DirectedEdge(int from, int to, double wt){
    this.from = from;
    this.to = to;
    this.wt = wt;
  }

  public int from(){
    return this.from;
  }

  public int to(){
    return this.to;
  }

  public double wt(){
    return this.wt;
  }

}
