package analysisMemory;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class ThreeSum {

	public static void find(int[] data, int sum){
		List<Integer> lst = new LinkedList<Integer>();
		Arrays.sort(data);
		for(int i=0; i<data.length; i++){
			for(int j=0; j<data.length; j++){
				int diff = sum -(data[i] + data[j]);
				int indx = Arrays.binarySearch(data,j+1,data.length,diff);
				if(indx >= 0) lst.add(data[indx]);
			}
		}
	}
}
