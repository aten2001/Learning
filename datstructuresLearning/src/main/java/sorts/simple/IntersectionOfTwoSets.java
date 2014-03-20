package sorts.simple;

import javax.xml.crypto.Data;

import analysisMemory.BinarySearch;

/**
 * Intersection of two sets. Given two arrays a[] and b[], each containing
 *  N distinct 2D points in the plane, design a subquadratic algorithm to count 
 *  the number of points that are contained both in array a[] and array b[].
 * @author gokulvanan
 *
 */
public class IntersectionOfTwoSets {

	static class Point implements Comparable<Point>{
		public Double x, y;
		
		@Override
		public boolean equals(Object p){
			if(p == null) return false;
			if(!(p instanceof Point)) return false;
			Point obj  = (Point) p;
			return (obj.x == this.x && obj.y == this.y);
		}

		@Override
		public int compareTo(Point o) {
			int diff = this.x.compareTo(o.x);
			if(diff != 0) return diff;
			else return this.y.compareTo(o.y);
		}
	}
	
	public int numberOfIntersects(Point[] first, Point[] second){
		Sorts<Point> sorter = new Sorts<>();
		sorter.shell(first); // N Log N -- worst case N^2/4
		sorter.shell(second);// N Log N -- worst case N^2/4
		BinarySearch searcher = new BinarySearch();
		int count =0;
		for(int i=0; i<first.length; i++){
			Point f = first[i];
			int indx  = searcher.search(first, 0, f); // Log N
			if(indx != -1) count++;
		}
		return count;
	}
	
	public static void main(String[] args){
		
	}
}
