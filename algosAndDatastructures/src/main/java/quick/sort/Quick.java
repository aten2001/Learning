package quick.sort;

import java.util.Arrays;

import sorts.simple.Shuffler;

public class Quick<Item extends Comparable<Item>> {


	public void quickSort(Item[] data){
		Shuffler<Item> shuffler = new Shuffler<>();
		shuffler.knutShuffle(data);
		recQuick(data, 0, data.length-1);
	}
	
	public void threeWayQuickSort(Item[] data){
		Shuffler<Item> shuffler  = new Shuffler<>();
		shuffler.knutShuffle(data);
		recQuick3Way(data, 0, data.length-1);
	}
	
	public Item quickSelect(Item[] data, int rank){
		Shuffler<Item> shuffler = new Shuffler<>();
		shuffler.knutShuffle(data);
		rank = rank - 1;
		int lo=0,hi=data.length-1;
		while (lo < hi){
			int mid = partion(data, lo, hi);
			if(mid < rank) lo = mid+1;
			else if (mid > rank) hi = mid;
			else return data[mid];
		}
		return null;
	}
	
	public Item quickSelect3Way(Item[] data, int rank){
		rank = rank-1;
		int lo=0, hi=data.length-1;
		while(lo < hi){
			int[] mid = partion3Way(data, lo, hi);
			int elm  = mid[0];
			if(elm < rank) lo = mid[0] + 1;
			else if (elm > rank) hi = mid[0];
			else return data[elm];
		}
		return null;
	}
	private int[] partion3Way(Item[] data, int lo, int hi){
		int i= lo+1, j=hi;
		while(i<= j){
			int cmp = data[i].compareTo(data[lo]);
			if(cmp < 0) swap(data, i++, lo++);
			else if(cmp == 0) i++;
			else swap(data,i,j--);
		}
		int [] output = {lo, j};
		return output;
	}
	private int partion(Item[] data, int lo, int hi){
		int i=lo; 
		int j=hi+1;
		while(true){
			while(lesser(data,++i,lo)){
				if(i == hi) break;
			}
			while(lesser(data,lo,--j)) {
				if(j == lo) break;
			}
			if(i >= j) break;
			else swap(data, i, j);
		}
		swap(data,lo,j);
		return j;
	}
	
	private void recQuick3Way(Item[] data, int low, int hi){
		if(low >= hi) return;
		int [] mids = partion3Way(data, low, hi);
		recQuick3Way(data, low, mids[0]-1);
		recQuick3Way(data, mids[1]+1, hi);
	}
	
	
	private void recQuick(Item[] data, int low, int hi){
		if(low >= hi) return;
		int mid = partion(data,low,hi);
		recQuick(data, low, mid-1);
		recQuick(data,mid+1,hi);
	}
	
	private void swap(Item[] data,int i, int j){
		Item buff = data[i];
		data[i] = data[j];
		data[j] = buff;
	}
	
	private boolean lesser(Item[] data, int i, int j){
		return data[i].compareTo(data[j]) < 0;
	}
	
	public static void main(String[] args){
		Integer[] data ={ 1,2,3,4,5,6,10};
		Quick<Integer> sorter = new Quick<>();
		System.out.println(sorter.quickSelect3Way(data, 4));
//		sorter.recQuick(data, 0, data.length-1);
	
	}
}
