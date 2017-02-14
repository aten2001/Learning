
public class ArrayStack {

  private T[] data;
  private int N;

  public ArrayStack(int size){
    this.N=0;
    this.data = (T []) new Object[size];
  }

  public void push(T data){
    if(N == data.length) resize(2 * N);
    this.data[N++]=data;
  }

  public T pop(){
    if(N == 0) throw new EmptyStackException();
    T val = data[--N];
    data[N] = null;
    if(N < data.lenght/4) resize(N / 2);
    return val;
  }

  private void resize(int size){
    T [] buff  = (T []) new Object[size];
    for(int i=0; i<N; i++){
      buff[i] = data[i];
    }
    data = buff;
  }

}



