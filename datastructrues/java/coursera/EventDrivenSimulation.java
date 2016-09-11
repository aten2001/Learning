
public class EventDrivenSimulation {


  public static void timeDrivenSimulation(Point[] points){

    while(true){
      stdraw.clear();
      for(int i=0; i<points.length; i++){
        points[i].updateIfCollision(dt); // internall runs another loop over points length
        points[i].move(dt);
        points.draw();
      }
    }
  }

  public static void eventDrivenSimulation(Point[] points){
    MinPQ pq = new MinPQ();
    for(int i=0; i<points.length; i++) predict(pq,points[i],points);
    pq.insert(new Event(0,null,null); //redraw

    while(!pq.isEmpty()){
      Event event = pq.delMin();
      if(!event.isValid()) continue;
      Point a = event.a;
      Point b = event.b;

      for(i=0; i<data.length; i++) points[i].move(event.time - t);
      //assumption that points[i].move - does not cause any colission 
      //to other points as this is the first event in MinPQ for this time 

      if(a != null && b!= null) a.bounceOff(b);
      else if (a != null & b == null) a.bounceOfVerticalWall();
      else if (a == null && b != nul) b.bounceOfHorizontalWall();
      else redraw();

      //repredict
      predict(pq,a,points);
      predict(pq,b,points);
  }

  private void predict(MinPQ pq, Point p, Points[] points){
    for(int i=0; i<N; i++){
      double dt = p.timeToHit(points[i]);
      pq.insert(new Event(t+dt, p,points[i]));
    }
    pq.insert(new Event(t+p.timeToHitVerticalWall(), p, null));
    pq.insert(new Event(t+p.timeToHitHorizontalWall(), null, p));
  }

}
