package unionFind;

/*
 * Union-find with specific canonical element. Add a method find() to the union-find data type so that find(i) returns the largest element in the connected component containing i. The operations, union(), connected(), and find() should all take logarithmic time or better.

	For example, if one of the connected components is {1,2,6,9}, then the find() method should return 9 for each of the four elements in the connected components.
 */
public class FindInCC {

	private int[] val ;
	private int[] size;
	private int [] largest;

	public FindInCC(int N){
		this.val= new int[N];
		this.size = new int[N];
		this.largest = new int[N];
		for(int i=0; i<N; i++) {
			this.val[i] = i;
			this.largest[i] = i;
			this.size[i] = 1;
		}
	}

	public void union(int first, int next) {
		int i = root(first);
		int j = root(next);
		if(i == j) return;
		
		int isz = this.size[i];
		int jsz = this.size[j];
		int imax = this.largest[i];
		int jmax = this.largest[j];
		if(isz < jsz) {
			this.val[i] = j;
			this.size[j] += this.size[i];
			this.largest[j] = (imax > jmax) ? imax : jmax;
		}else{
			this.val[j] = i;
			this.size[i] += this.size[j];
			this.largest[i] = (jmax > imax) ? jmax : imax;
		}
	}

	public boolean connected(int first, int next) {
		return root(first) == root(next);
	}
	
	
	private int root(int i){
		int v = val[i];
		if(v != i){
			val[i] = val[v];
			root(v);
		}
		return v;
	}


}
