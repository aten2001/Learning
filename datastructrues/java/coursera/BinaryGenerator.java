import java.util.Arrays;

public class BinaryGenerator{


  private byte aux[];

  public void generate(int N){
    aux = new byte[N];
    gen(N);
  }

  private void gen(int N){
    if(N < 1){
      System.out.println(Arrays.toString(aux));
    }else{
      aux[N-1]=0;
      gen(N-1);
      aux[N-1]=1;
      gen(N-1);
    }
  }
  
  public static void main(String[] args){
    BinaryGenerator bg = new BinaryGenerator();
    bg.generate(3);
  }

}

