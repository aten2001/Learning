package unionFind;

/**
 * Union-by-size. Develop a union-find implementation that uses the same basic 
 * strategy as weighted quick-union but keeps track of tree height and always
 *  links the shorter tree to the taller one. Prove a lgN upper bound on the height 
 *  of the trees for N sites with your algorithm.
 * @author gokulvanan
 *
 */
public class UnionBySize {
	private int[] val ;
	private int[] size;

	public UnionBySize(int N){
		this.val= new int[N];
		this.size = new int[N];
		for(int i=0; i<N; i++) {
			this.val[i] = i;
			this.size[i] = 1;
		}
	}

	public void union(int first, int next) {
		int i = root(first);
		int j = root(next);
		if(i == j) return;
		
		int isz = this.size[i];
		int jsz = this.size[j];
		if(isz < jsz) {
			this.val[i] = j;
		}else{
			this.val[j] = i;
			if(isz == jsz) this.size[i]++; // increment only when sizes are same
		}
	}

	public boolean connected(int first, int next) {
		return root(first) == root(next);
	}
	
	
	private int root(int i){
		int v = val[i];
		if(v != i){
			//TODO check how length could be altered when using path compression
			//val[i] = val[v];
			//this.size[i]--;
			root(v);
		}
		return v;
	}
}
