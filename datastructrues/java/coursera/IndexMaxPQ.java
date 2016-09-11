

public class IndexMaxPQ {

  private int[] pq;
  private int[] qp; // reveres map pq[qp[i]] = qp[pq[i]]
  private Key[] keys;
  private int N;

  public IndexMaxPQ(int N){
    this.keys = new Key[N+1];
    this.pq = new int[N+1];
    this.qp = new int[N+1];
    for(int i=0; i<N+1; i++) this.qp[i] = -1;
    this.N=0;
  }
  //associates key to an index
  public void insert(int i, Key key){
    N++;
    qp[i]=N;
    pq[N]=i;
    keys[i]= key;
    swim(N);
  }


  //removes max key and returns its associated index
  public int delMax(){
    int min = pq[1];
    swap(1,N--);
    sink(1);
    qp[min] = -1;
    keys[pq[N+1]] = null;
    pq[N+1] = -1;
    return min;

  }

  //returns maxIndex associated with a key
  public int maxIndex(){
    return pq[1];

  }

  //returns the max key
  public Key maxKey(){
    return keys[pq[1]];
  }

  //returns key associated with index i
  public Key keyOf(int i){
    return keys[i];

  }

  // change key associated with specified index to new val
  public void changeKey(int i, Key k){
    keys[i]=k;
    swim(qp[i]);
    sink(qp[i]);

  }

  public void increaseKey(int i, Key k){
    keys[i] = k;
    swim(qp[i]);
  }

  public void decreaseKey(int i, Key k){
    keys[i] = k;
    sink(qp[i]);
  }


  private void swim(int k){
    while(k >=1 && less(k/2,k)){
      swap(k,k/2);
      k = k/2;
    }
  }

  private void sink(int k){
    while(2*k <=N){
      int child = less(2*k, (2*k)+1)? (2*k)+1 : 2*k;
      if(less(k,child)){
        swap(k,child);
        k=child;
      }else break;
    }
  }

  private void less(int a, int b){
    return (keys[pq[a]].compareTo(keys[pq[b]]) < 0;
  }

  private void swap(int a, int b){
    int buff = pq[a];
    pq[a] = pq[b];
    pq[b] = buff;
    qp[pq[a]] = a;
    qp[pq[b]] = b;
  }
}
