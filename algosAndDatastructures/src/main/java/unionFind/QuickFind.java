package unionFind;


/**
 * Simple class that implements QuickFind Algo
 *
 **/

public class QuickFind implements UF{

	int[] data = null;

	public QuickFind(int N){
		this.data = new int[N];
		for(int i=0; i<N; i++){
			this.data[i] = i;
		}
	}

	public boolean connected(int first, int next){
		return this.data[first] == this.data[next];
	}

	public void union(int first, int next){
		int firstVal = this.data[first];
		int nextVal = this.data[next];

		for(int i=0; i<data.length; i++){
			if(this.data[i] == firstVal) this.data[i]=nextVal;
		}
	}

}
