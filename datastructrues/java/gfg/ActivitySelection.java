import java.util.*;

//http://practice.geeksforgeeks.org/problems/activity-selection/0
public class ActivitySelection {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while(T-- > 0){
          int N = sc.nextInt();
          Window[] w = new Window[N];

          for(int i=0; i<N; i++){
              w[i] = new Window(sc.nextInt());
          }

          for(int i=0; i<N; i++){
              w[i].stop = sc.nextInt();
          }

          Arrays.sort(w);

          int max = maxtask(0,new Window(-1),w);
          System.out.println(max);
        }
    }


    static int maxtask(int i, Window selected, Window[] w){
      System.out.println(selected.count);
      System.out.println(selected.val);
        if(i == w.length) return 0;
        Window n = w[i];
        if(selected.canDo(n)){
          System.out.println("here");
            return Math.max((1+maxtask(i+1,selected.add(n),w)), (maxtask(i+1,selected,w)));
        }else{
            return maxtask(i+1,selected,w);
        }
    }

    static class Window implements Comparable<Window>{
        int start;
        int stop;
        int count = 0;
        String val ="";

        public Window(int start){
            this.start = start;
        }
        @Override
        public int compareTo(Window o) {
          return this.start - o.start;
        }

        public Window(Window old){
          this.start = old.start;
          this.stop = old.stop;
          this.count +=1;
          this.val += "("+start+"-"+stop+")";
        }

        boolean canDo(Window n){
            if(this.start == -1) return true;
            if(n.start >= this.start && n.start <= this.stop) return false;
            if(n.stop >= this.start && n.stop <= this.stop) return false;
            return true;
        }

        Window add(Window n){
          if(this.start == -1) return new Window(n);
          int start = (n.start < this.start) ? n.start : this.start;
          Window o = new Window(start);
          int stop = (n.stop > this.stop) ? n.stop : this.stop;
          o.stop = stop;
          o.count=this.count+1;
          o.val += this.val+" ("+n.start+"-"+n.stop+")";
          return o;
        }
    }

}
