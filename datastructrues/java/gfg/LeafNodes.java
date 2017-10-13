import java.util.*;

public class LeafNodes {

    static class Bound{

        private int val;
        private int min;
        private int max;
        private boolean parent = false;

        public Bound(int min, int val, int max){
            this.min = min;
            this.val = val;
            this.max = max;
        }

        public boolean checkParent(int nv){
            boolean compute  = (nv > min && (max == -1 || nv < max));
            return compute;
        }

        public boolean isLeaf(){
            return !this.parent;
        }

        public int getVal(){
            return this.val;
        }

        public Bound buildChild(int nv){
            this.parent = true;
            if(nv < val) return new Bound(this.min, nv, this.val);
            else return new Bound(this.val, nv, this.max);
        }
        
        public String toString(){
            return this.val+",["+this.min+","+this.max+"],"+this.parent;
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while(T-- > 0){
            int N = sc.nextInt();
            Stack<Bound> stack = new Stack<>();
            for(int i=0; i<N; i++){
                int val = sc.nextInt();
                while(!stack.isEmpty()){
                    Bound old = stack.peek();
                    if(old.checkParent(val)){
                        stack.push(old.buildChild(val));
                        break;
                    }else{
                        Bound buff = stack.pop();
                        if(buff.isLeaf()){
                            System.out.print(buff.getVal()+" ");
                        }
                    }
                }
                if(stack.isEmpty()){
                    stack.push(new Bound(0,val,-1));
                }
            }

            if(!stack.isEmpty()){
                Bound buff = stack.pop();
                if(buff.isLeaf()){
                    System.out.print(buff.getVal());
                }
            }
            System.out.println();
        }
    }
}
