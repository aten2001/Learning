

public class SweepLineAlgo {

  static class Line {
    Point a, b;

    public Line (Point x, Point y){
      this.a = x;
      this.b = y;
    }
  }

  static class PointWrap extends Comparable{
    Point p;
    Line line;

    public PointWrap(Line ln, Point p){
      this.line = ln;
      this.p=p;
    }

    public int toCompare(PointWrap other){
      if(this.p.x < other.p.x) return -1;
      else if (this.p.x > other.p.x) return 1;
      else return 0;
    }
  }

  public List<Point> intersections(List<Line> lines){

    MinPQ pq = new MinPQ();
    for(Line ln : lines){
      pq.insert(new PointWrap(ln,ln.a);
      if(!ln.isVertical()){
        pq.insert(new PointWrap(ln,ln.b);
      }
    }

    LLRBST<Double bst = new LLRBST();
    Queue<String> intersectingLines = new Queue<String>();
    while(!pq.isEmpty()){
      PointWrap pw = pq.delMin();
      Point p = pq.p;
      Line ln = pq.line;
      if(ln.isVertica()){
        Queue<Integer> lst =  bst.keys(ln.a.y,ln.b.y);
        for(Integer y : lst){
          if(y == ln.a.y || y == ln.b.y) continue;
          intersects.enque(bst.get(y).ln+"_"+ln);
        }
      }else{
        bst.put(p.y,pw);
      }
    }
  }

}
