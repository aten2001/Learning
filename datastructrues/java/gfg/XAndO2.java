import java.util.*;


public class XAndO2 {

    static class Tupple {
        int i;
        int j;

        public Tupple(int i, int j){
            this.i = i;
            this.j = j;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + i;
            result = prime * result + j;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Tupple other = (Tupple) obj;
            if (i != other.i)
                return false;
            if (j != other.j)
                return false;
            return true;
        }

        public String toString(){
            return "["+i+","+j+"]";
        }

    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while(T-- > 0){

            int N = sc.nextInt();
            int M = sc.nextInt();
            boolean[][] data = new boolean[N][M];
            List<Tupple> changeableLst = new ArrayList<>();
            Set<Tupple> cantChange = new HashSet<>();
            for(int i=0; i<N; i++){
                for(int j=0;j<M; j++){
                    boolean isx = "X".equals(sc.next());
                    data[i][j] = isx;
                    if(!isx){
                        if(i == 0 || j == 0 || i == N-1 || j == M-1) {
                            cantChange.add(new Tupple(i,j));
                        }else{
                            changeableLst.add(new Tupple(i,j));
                        }
                    }
                }
            }
            //System.out.println(cantChange);
            Set<Tupple> canChange = new HashSet<>();
            for(Tupple t : changeableLst){
                if(canChange.contains(t) || cantChange.contains(t)) continue;
                else check(t,data,canChange,cantChange);
            }

            for(int i=0; i<N; i++){
                for(int j=0; j<M; j++){
                    if(data[i][j]) System.out.print("X ");
                    else if(canChange.contains(new Tupple(i,j))) System.out.print("X ");
                    else System.out.print("O ");
                }
            }
            System.out.println();
        }

    }


    private static void check(Tupple t,boolean[][] data,
            Set<Tupple> canChange, Set<Tupple> cantChange){
            if(canChange.contains(t) || cantChange.contains(t)) return;
            Queue<Tupple> lst = new LinkedList<>();
            lst.add(t);
            Set<Tupple> marked = new HashSet<>();
            marked.add(t);
            boolean changeFlag = true;
            L1: while(!lst.isEmpty()){
                Tupple nt = lst.poll();
                //System.out.println(nt);
                for(Tupple a : adjTupples(nt,data)){
                    if(marked.contains(a)) continue;
                    if(cantChange.contains(a)){
                        changeFlag = false;
                        break L1;
                    }
                    marked.add(new Tupple(a.i,a.j));
                    lst.add(new Tupple(a.i,a.j));
                }
            }

            if(changeFlag){
                canChange.addAll(marked);
            }else {
                cantChange.addAll(marked);
            }
    }

    private static List<Tupple> adjTupples(Tupple t, boolean[][] data){
        List<Tupple> lst = new ArrayList<>();
        if(!data[t.i-1][t.j]) lst.add(new Tupple(t.i-1, t.j));
        if(!data[t.i][t.j-1]) lst.add(new Tupple(t.i, t.j-1));
        if(!data[t.i][t.j+1]) lst.add(new Tupple(t.i, t.j+1));
        if(!data[t.i+1][t.j]) lst.add(new Tupple(t.i+1, t.j));
        return lst;
    }


}
