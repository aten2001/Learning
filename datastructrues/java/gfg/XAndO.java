import java.util.*;

public class XAndO {

    static class Tupple {
        int i, j;

        public Tupple(int i, int j){
            this.i = i;
            this.j = j;
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while(T-- > 0) {
            int N = sc.nextInt();
            int M = sc.nextInt();
            char[][] data = new char[N][M];
            List<Tupple> edges = new ArrayList<>();
            for(int i=0; i<N; i++){
                for(int j=0; j<M;j++){
                    char v = "O".equals(sc.next()) ? '-' : 'X';
                    data[i][j] = v;
                    if(v == '-'){
                        if(i == 0 || i == N-1 
                                || j == 0 || j == M-1){
                            edges.add(new Tupple(i,j));
                        }
                    }
                }
            }

            for(Tupple t : edges){
                updateO(data,t.i,t.j);
            }

            for(int i=0; i<N; i++){
                for(int j=0; j<M; j++){
                    if(data[i][j] == '-') System.out.print("X ");
                    else System.out.print(data[i][j]+" ");
                }
            }
            System.out.println();
        }
    }

    private static void updateO(char[][] data, int i, int j){
        if(i<0 || j <0 || i> data.length-1 || j> data[0].length-1) return;
        if(data[i][j] != '-')  return;
        data[i][j] = 'O';
        updateO(data,i-1,j);
        updateO(data,i+1,j);
        updateO(data,i,j-1);
        updateO(data,i,j+1);
    }

}

            
