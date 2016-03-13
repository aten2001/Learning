

public class ConvexHull {

  private static class Point {
    public double x,y;
    public Point(double x, double y){
      this.x = x;
      this.y = y;
    }
    

    public AngleComparator getPolarAngleComparator(){
      return new AngleComparator();
    }

    class AngleComparator implements Comparator<Point>{

      private Point a = Point.this; //refrences the invoking super class

      private boolean ccw(Point a, Point b, Point c) {
        double D = (b.x-a.x) * (c.y-a.y) -  ((b.y-a.y) * (c.x-a.x));
        return D > 0;
      }

      public int compare(Point b, Point c){
       return -ccw(a,b,c);
      }

    }
  }

  private Stack<Integer> convexStack;

  private boolean ccw(Point a , Point b, Point c) {
    double D = (b.x-a.x) * (c.y-a.y) -  ((b.y-a.y) * (c.x-a.x));
    return D > 0;
  }

  public ConvexHull(Point[] points){
   Point[] copy = new Point[points.length];
   Point minY = new Point(Doube.INFINITY,Double.INFINITY);
   for(int i=0; i< points.lenght;i++) {
     Point p = points[i];
     if(p.y < minY.y) minY = p;
     copy[i] = p;
  }

  Arrays.sort(copy, minY.getPolarAngleComparator()); //Sort by polar angle

  convexStack = new Stack<Integer>();
  Point a = points[0];
  Point b = points[1];
  stack.push(a);
  stack.push(b);
  for(int i=2; i< points.length; i++){
    Point c = point[i];
    if (ccw(a,b,c)){
      stack.push(c);
      a=b;
      b=c;
    }else {
      stack.pop();
      stack.push(c);
      b=c;
    }
  }
  }

  public Iterable<Point> convexHull(){
    return convexStack;
  }

}



