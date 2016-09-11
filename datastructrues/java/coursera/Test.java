

public class Test{

  public static void main(String[] args){
    int N = 12;
    for(int i=1; i<N; i+=i){
      System.out.println("");
      for(int j=0; j<N-i; j+=i+i){
        System.out.println(j+"_"+(j+i-1)+"_"+(j+i+i-1));
      }
    }
  }

}
