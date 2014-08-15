
import java.util.PriorityQueue;
import java.util.HashMap;

/**
 * Design a parking lot system where you need to provide a token with the parking space number on it to each new entry for the space closest to the entrance. 
 * When someone leave you need update this space as empty. 
 * What data structures will you use to perform the closest empty space tracking, plus finding what all spaces are occupied at a give time.
 **/

public class ParkingToken {

  static class Space {
    private int id;
    private double dist;

    public Space(int id, double dist){
      this.id = id;
      this.dist = dist;
    }
  }

  private PrioriyQueue<Space> pq = null;
  private HashMap<Integer,Space> map = null;

  public ParkingToken(int N){
    pq = new PriorityQueue<>();
    map = new HashMap<>();
    for(int i=0; i<N; i++){
      Space sp = new Space(i,Math.random(i));
      pq.add(sp);
    }
  }

  public int getToken(){
    Space sp = pq.poll();
    map.put(sp.id,sp);
    return sp.id;
  }

  public void deleteToken(int id){
    Space sp = map.remove(id);
    pq.add(sp);
  }

  public int occupiedCount(){
    return map.size();
  }
}

