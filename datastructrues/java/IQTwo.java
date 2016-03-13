
public class IQTwo {

  static class UF {

    int[] data;
    int[] size;
    int max;

    public UF(int N){
      this.data = new int[N];
      this.max=0;
      this.size = new int[N];
      for(int i=0; i<N; i++){
        this.data[i]=i;
        this.component=-1;
      }
    }

    public void union(int a , int b){
      int i = root(a);
      int j = root(b);
      if(connected(i,j)
      if(size[a] > size[b]){
        data[j] = i;
        size[i] += size[j];
        max = (size[max] < size[i])? i: max;
      }
      else{
        data[i] = j;
        size[j] += size[i];
        max = (size[max] < size[j])? j: max;
      }
    }

    public int root(int i){
      while(data[i] != i){
        data[i] = data[data[i]]; // path compression
        i = data[i];
      }
      return i;
    }

    public boolean connected(int a, int b){
      return root[a] == root[b];
    }

    public Iterable<Integer> maxContiguousBlock(){
      LinkedList<Integer> list = new LinkedList<>();
      for(int i=0; i<data.length(); i++){
        if(root(i) == max) list.add(i);
      }
    }
  }

  UF uf = new UF(256);

  public void contiguousBlock(int[] data){
    boolean[] marked = new boolean[data.length()];
    for(int i=0; i<data; i++){
      int val = data[i];
      marked[val]=true;
      if(marked[val+1])uf.union(val, val+1);
      if(marked[val-1] && val != 0) uf.union(val,val-1);
    }
    Iterable<Integer> output =  maxContiguousBlock();
    for(int  i : output){
      System.out.printlnt(i);
    }

  }

  public static void main(String[] args){

  }

}
