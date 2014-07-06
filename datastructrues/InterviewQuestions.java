
public class InterviewQuestions {

  public drawGraph(Graph G){

    int x =0;
    for(int v : new ToplogicalOrder(G).order){
      map.put(v,x);
      garphics.drawPoint(x,y);
      x +=3;
    }

    for(int v : new ToplogicalOrder(G).order){
      for(int w : G.adj(v)){
        int x1 = map.get(v), x2 = map.get(w);
        int hop = (x2 - x1)/3;
        graphics.drawCurveLine(x1,x2,hop-1);
      }
    }
  }


  //TODO 
  //ArrayHOpper
  //FrogJump
  public void frogJump(int N, int[]speeds, int[]startPost){
    //letTime

  }


  public int areaFinder(char[][] matrix, char s, Point p){
    Queue<Point> points = new Queue<Point>();
    int colSz = matrix[0].length;
    boolean[] marked = new boolean[matrix.length * colSz];
    int v = p.oneD();
    marked[v] = true;
    points.enque(p);
    int area = 1;
    while(!points.isEmpty()){
      Point po = points.deque();
      for(Point apo : adjacent(po)){
        if(marked[apo.oneD(colSz)]) continue;
        maked[apo.oneD(colSz)]=true;
        points.enque(apo);
        area++;
      }
    }
    return area;
  }

  static class Point{
    int x,y;
    Point(int x, int y){
      this.x=x;
      this.y=y;
    }

    public int oneD(int sz){
      return (this.x * sz) + (y);
    }
  }

  private Iterable<Point> adjacent(Point p){
    Queue<Point> queue = new Queue<Point>();
    for(int i=p.x-1; i<=p.x+1; i++){
      for(int j=p.y-1; j<=p.y+1; j++){
        queue.enque(new Point(i,j));
      }
    }
    return queue;
  }

}

}


