package analysisMemory;

import java.util.Arrays;

/**
 * Search in a bitonic array. An array is bitonic if it is comprised of an increasing sequence of integers followed 
 * immediately by a decreasing sequence of integers. Write a program that, given a bitonic array of N distinct integer values, determines whether a given integer is in the array.
Standard version: Use 3lgN compares in the worst case.
Signing bonus: Use 2lgN compares in the worst case (and prove that no algorithm can guarantee to perform fewer than 
2lgN compares in the worst case).

 * @author gokulvanan
 *
 */
public class BitonicArray {
	
	public boolean isIntegerInTheArray(int[] data, int a){
		int largestLoc = findLargest(data);
		if(largestLoc == -1) throw new RuntimeException("erro in find largest");
		return (binarySearch(data, 0, largestLoc, a, false) ||
				binarySearch(data, largestLoc, data.length-1, a, true));
	}
	
	//NOTE THIS will fail under duplicates
	public static int findLargest(int[] data){
		int lo=0, hi=data.length-1;
		int mid = (lo+hi)/2;
		int N = data.length-1;
		while (lo <= hi){
			int val = data[mid];
			if(mid == 0 ) return (val > data[mid+1]) ?  mid : -1;
			else if(mid == N) return (val > data[mid-1]) ? mid : -1;
			else{
				int prev = data[mid-1];
				int next = data[mid+1];
				if(prev < val && val < next) lo=mid+1;
				else if(prev > val && val > next) hi = mid-1;
				else return mid; // prev > val && val > next  // is the only other case
				mid = (lo+hi)/2;
			}
		}
		return -1;
	}
	
	public static boolean binarySearch(int[] data, int lo, int hi, int elm, boolean reverse){
		int mid = (lo + hi) /2;
		while(lo <= hi){
			int val = data[mid];
			if(val == elm)  return true;
			else if (val < mid) {
				if(reverse) lo = mid + 1;
				else hi = mid;
			}
			else{
				if(reverse) hi = mid;
				else lo = mid+1;
			}
			mid = (lo + hi) / 2;
		}
		return false;
	}

}
