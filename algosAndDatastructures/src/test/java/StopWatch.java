
/**
 * Utilty class to measure performance
 **/


public class StopWatch{

  private long start = 0L;

  public StopWatch(){
    this.start=System.currentTimeMillis();
  }

  public void print(){
    System.out.println("Duration : "+ (System.currentTimeMillis() - this.start));
  }

  public void stop(){
    print();
    this.start=0;
  }

  public void reset(){
    this.start=0;
  }

}
