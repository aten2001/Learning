package unionFind;

/*
 * Social network connectivity. 
 * Given a social network containing N members and a log file containing 
 * M timestamps at which times pairs of members formed friendships, 
 * design an algorithm to determine the earliest time at which all 
 * members are connected (i.e., every member is a friend of a 
 * friend of a friend ... of a friend). Assume that the log file is sorted
 * by timestamp and that friendship is an equivalence relation.
 * The running time of your algorithm should be MlogN or better 
 * and use extra space proportional to N.
 */
public class SocialNetworkConnectivity {

	private int[] val ;
	private int[] size;
	private int connectedComponents;

	public SocialNetworkConnectivity(int N){
		this.val= new int[N];
		this.size = new int[N];
		for(int i=0; i<N; i++) {
			this.val[i] = i;
			this.size[i]=1;
		}
		
		this.connectedComponents = N;
	}

	public void union(int first, int next) {
		int i = root(first);
		int j = root(next);
		if(i == j) return;
		connectedComponents--; // check number of connected components
		int isz = this.size[i];
		int jsz = this.size[j];
		if(isz < jsz) {
			this.val[i] = j;
			this.size[j] += this.size[i];
		}else{
			this.val[j] = i;
			this.size[i] += this.size[j];
		}
	}

	public boolean connected(int first, int next) {
		return root(first) == root(next);
	}

	public boolean isFullyConnected(){
		return (this.connectedComponents == 0);
	}
	private int root(int i){
		int v = val[i];
		if(v != i){
			val[i] = val[v];
			root(v);
		}
		return v;
	}



	public static void main(String... args){
		//TODO add code to read file and perform Union operation
		new SocialNetworkConnectivity(10).isFullyConnected();

	}



}
