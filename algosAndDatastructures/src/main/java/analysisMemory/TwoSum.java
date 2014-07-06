package analysisMemory;

import java.util.ArrayList;
import java.util.Collections;

public class TwoSum {

	ArrayList<Integer> data = null;
		
	public TwoSum(){
		data = new ArrayList<Integer>();
	}
	public void add(int item){
		data.add(item);
	
	}
	
	
	public boolean find(int sum){
		Collections.sort(data);
		for(int low=0; low<data.size(); low++){
			int v =BinarySearch.search(data,low, sum-data.get(low));
			if(v == -1) continue;
			else return true;
		}
		return false;
	}
}
