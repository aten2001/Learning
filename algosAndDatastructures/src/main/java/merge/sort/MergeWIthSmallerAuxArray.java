package merge.sort;

import java.util.Arrays;

/**
 * Merging with smaller auxiliary array. Suppose that the subarray a[0] to a[N-1] 
 * is sorted and the subarray a[N] to a[2*N-1] is sorted. How can you merge
 * the two subarrays so that a[0] to a[2*N-1] is sorted using an auxiliary
 * array of size N (instead of 2N)?
 * Hint: copy only the left half into the auxiliary array.
 * @author gokulvanan
 *
 */
public class MergeWIthSmallerAuxArray {

	public void merge(int [] data){
		int N = data.length/2;
		int [] aux = new int[N];
		
		for(int i=0; i<N; i++) aux[i] = data[i];
		
		int i=0, j = N;
		for(int k=0; k<data.length; k++){
			if(i > N) data[k] = data[j++];
			else if(j > data.length-1) data[k] = aux[i++];
			else if(aux[i] < data[j]) data[k] = aux[i++];
			else data[k] = data[j++];
		}
		
	}
	
	public static void main(String... args){
		int [] data = {1,24,34,5,10,25};
		MergeWIthSmallerAuxArray M = new MergeWIthSmallerAuxArray();
		M.merge(data);
		System.out.println(Arrays.toString(data));
	}
}
