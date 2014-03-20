package merge.sort;

import java.util.Arrays;

/**
 * Counting inversions. An inversion in an array a[] is a pair of entries a[i] and a[j] 
 * such that i<j but a[i]>a[j]. Given an array, design a linearithmic algorithm to count 
 * the number of inversions
 *Hint: count while mergesorting.
 * @author gokulvanan
 *
 */
public class CountInversions {

	int count=0; 
	public  int inversionCount(int[] data){
		int[] aux = new int[data.length];
		recCount(data,aux,0,data.length-1);
		return count;
	}
	
	
	private void recCount(int[] data, int[] aux, int lo, int hi){
		if (lo >= hi) return;
		int mid = (lo+ hi) / 2;
		recCount(data, aux, lo, mid);
		recCount(data, aux, mid+1, hi);
		merge(data,aux,lo,mid,hi);
	}

	private void merge(int[] data, int[] aux, int lo, int mid, int hi) {
		if(data[mid] < data[mid+1]) return;
		for(int i=lo; i<=hi; i++) aux[i] = data[i];
		
		int i=lo, j=mid+1;
		for(int k=lo; k<=hi; k++){
			if(i > mid) data[k] = aux[j++];
			else if(j > hi) data[k] = aux[i++];
			else if(aux[i] > aux[j]){
				data[k] = aux[j++];
				count++; // inversion count;
			}
			else data[k] = aux[i++];
		}

	}
	
	public static void main(String[] args){
		CountInversions counter = new CountInversions();
		int[] data ={3, 51 ,4, 2, 111 ,32 ,1 };
		counter.inversionCount(data);
		System.out.println(Arrays.toString(data));
		System.out.println(counter.count);
	}
}
