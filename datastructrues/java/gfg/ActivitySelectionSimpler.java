import java.util.*;

//http://practice.geeksforgeeks.org/problems/activity-selection/0
public class ActivitySelectionSimpler {

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
          System.out.println(Arrays.toString(w));

          int temp = 0;
          int count = 1;
          for(int i=1; i<N; i++){
              if(w[i].start >= w[temp].stop){
                  temp = i;
                  count++;
              }
          }
          System.out.println(count);
        }
    }


    static class Window implements Comparable<Window>{
        int start;
        int stop;

        public Window(int start){
            this.start = start;
        }
        @Override
        public int compareTo(Window o) {
          return this.start - o.start;
        }

    }

}
