/*
 * https://www.hackerrank.com/contests/stryker-codesprint/challenges/point-filtering
 */

import java.text.DecimalFormat;
import java.util.*;


public class PointFiltering {  
    
    
    static class NotFoundException extends Exception{
        private static final long serialVersionUID = 1L;
    }
    static class EmptyMainListException extends Exception{

        private static final long serialVersionUID = 2L;
    }

    static class PointSortCompartor implements Comparator<Point> {

        @Override
        public int compare(Point o1, Point o2) { //reverse sort
            if(o2.z > o1.z) return 1;
            if(o2.z < o1.z) return -1;
            return 0;
        }
        
    }
    
    private static PointSortCompartor compartor = new PointSortCompartor();
    static class Point{
        public final int k;
        public final double x;
        public final double y;
        public final double z;

        public Point(int k, double x,
                double y, double z){

            this.k = k;
            this.x = x;
            this.y = y;
            this.z = z;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + k;
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
            Point other = (Point) obj;
            if (k != other.k)
                return false;
            return true;
        }
        
        @Override
        public String toString(){
            DecimalFormat df = new DecimalFormat("#.000"); 
            return k+" = ("+df.format(x)+","+df.format(y)+","+df.format(z)+")";
        }

    }

    static class MainList{
        private final Point[] points;
        private int index;

        public MainList(int N){
            this.points = new Point[N];
            this.index= 0;
        }

        public void addPoint(Point p){
            this.points[index++] = p;
        }

        public void sort(){
            Arrays.sort(points,compartor);
            this.index = 0;
        }
        public boolean hasEntry(){
            return index < this.points.length;
        }

        public Point removeMaxPoint(){
            return this.points[index++];
        }
    }

    static class Bucket {
        private final HashMap<Integer,Point> points;
        private final MainList mainList;
        private final int b;

        public Bucket(MainList list, int size){
            this.points = new HashMap<Integer,Point>(2*size);
            this.mainList = list;
            this.b = size;
            for(int i=0; i<size; i++){
                if(mainList.hasEntry()){
                    Point p = this.mainList.removeMaxPoint();
                    this.points.put(p.k,p);
                }
            }
        }

        public Point find(int k) throws NotFoundException{
            if(this.points.containsKey(k)){
                return this.points.get(k);
            }
            throw new NotFoundException();
        }
        
        public void remove(int k) throws NotFoundException, EmptyMainListException {
            if(this.points.containsKey(k)){
                if(mainList.hasEntry()){
                    this.points.remove(k);
                    Point newPoint = this.mainList.removeMaxPoint();
                    this.points.put(newPoint.k, newPoint);
                    return ;
                }else{
                    throw new EmptyMainListException();
                }
                
            }
             throw new NotFoundException();
        }
        
    }

    static interface Command {
        void execute(Bucket bucket) throws NotFoundException, EmptyMainListException;
    }
    
    static class FindCommand implements Command{

        private final int k;
        public FindCommand(int k) {
            this.k = k;
        }

        @Override
        public void execute(Bucket bucket)throws NotFoundException, EmptyMainListException {
            Point p = bucket.find(this.k);
            System.out.println(p);
        }
        
    }
    
    static class RemoveCommand implements Command{

        private final int k;
        public RemoveCommand(int k) {
            this.k = k;
        }

        @Override
        public void execute(Bucket bucket) throws NotFoundException, EmptyMainListException{
            bucket.remove(this.k);
            System.out.println("Point id "+k+" removed.");
            
        }
        
    }
    static class CommandParser {
        
        private final Command cmd;
        public CommandParser(String query){
            //assuming restricted commands no defensive checks
            String[] buff = query.split(" ");
            int k = Integer.parseInt(buff[1]);
            if("F".equalsIgnoreCase(buff[0])){
                this.cmd = new FindCommand(k);
            }else{
                this.cmd = new RemoveCommand(k);
            }
        }
        
        public Command getCommand(){
            return this.cmd;
        }
        
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int bucketSize = sc.nextInt();
        MainList main = new MainList(N);
        for(int i=0; i<N; i++){
            int k = sc.nextInt();
            double x = sc.nextDouble();
            double y = sc.nextDouble();
            double z = sc.nextDouble();
            Point p = new Point(k,x,y,z);
            main.addPoint(p);
        }
        main.sort();
        Bucket bucket = new Bucket(main, bucketSize);
        sc.nextLine(); //skip to next line
        String query = sc.nextLine();
        while(query != null){
//          System.out.println(query);
            CommandParser cmdParser = new CommandParser(query);
            Command cmd = cmdParser.getCommand();
            try{
                cmd.execute(bucket);
            }catch(NotFoundException n){
                System.out.println("Point doesn’t exist in the bucket");
            }catch(EmptyMainListException e){
                System.out.println("No more points can be deleted");
            }
            try{
                query = sc.nextLine();
            }catch(NoSuchElementException n){
                query = null;
            }
        }
        sc.close();
    }
}


