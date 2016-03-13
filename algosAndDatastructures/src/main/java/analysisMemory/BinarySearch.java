package analysisMemory;

import java.util.ArrayList;

public class BinarySearch<Item extends Comparable<Item>>{

	public static int search(ArrayList<Integer> data, int lo, int search){
		int hi = data.size()-1; 
		while(lo <= hi){
			int mid = (lo + hi) / 2;
			int val = data.get(mid);
			if(val < search) hi = mid - 1;
			else if (val > search) lo = mid + 1;
			else return val;
		}
		return -1;
	}
	
	public int search(Item[] data, int lo, Item search){
		int hi = data.length-1; 
		while(lo <= hi){
			int mid = (lo + hi) / 2;
			Item val = data[mid];
			int cmp = val.compareTo(search);
			if(cmp < 0) hi = mid - 1;
			else if (cmp > 0) lo = mid + 1;
			else return mid;
		}
		return -1;
	}
	
}
