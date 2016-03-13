

public class ArrayQueue {

  private T [] data;
  private int head, tail;
  private int N;

  public ArrayQueue(int sz){
    this.data = (T []) new Object[sz];
    this.head = 0;
    this.tail = 0;
    this.N=0;
  }


  public void enque(T val){
    if(N == data.length) resize(2 * N);
    int indx  = tail % data.length;
    data[indx] = val;
    tail++;
    N++;
  }

  public T deque(){
    if(N == 0) throw new EmptyStackException();
    int indx  = head % data.length;
    T val = data[indx];
    data[indx] = null;
    head++;
    N--;
    if(N <= data.length/4) resize( N / 2);
    return val;
  }

  private void resize(int size){
    T buff = (T[]) new Object[size];
    for(int i=head,j=0; i<tail;i++){
      int indx = i % data.length;
      buff[j++] = data[indx];
    }
    data = buff;
    head = 0;
    tail = N;
  }

}
