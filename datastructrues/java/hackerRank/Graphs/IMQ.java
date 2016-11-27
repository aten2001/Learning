
public class IMQ {

  static class Tupple{
    public int index;
    public int val;

    public Tupple(int index, int val){
      this.index = index;
      this.val = val;
    }
  }

  private ArrayList<Integer> data;
  private ArrayList<Integer> pq;
  private ArrayList<Integer> qp;
  private int N = 1;

  public void upsert(int index, int val){
    boolean update = data[index] != null;
    data[index] = val;
    if(update){
      int heapIdx = qp[index];
      swim(heapIdx);
      sink(heapIdx);
    }else{
      pq[N] = index;
      qp[index] = N;
      swim(N);
      N++;
    }
  }

  public Tupple delMax(){
    if (data[1] == null) throw new EmptyException();
    :

  }

  public Tupple peekMax();

  public int size();

  public int contains(int index);
}
