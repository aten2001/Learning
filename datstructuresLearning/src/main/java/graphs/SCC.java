package graphs;

import java.util.Stack;

//Kosiraju and shair algo

public class SCC {

	private int index[] = null;
	private int count;
	private boolean[] marked = null;
	
	public SCC(DiGraph G){
		this.index = new int[G.V()];
		this.marked = new boolean[G.V()];
		this.count = 0;
		DFSOrder order = new DFSOrder();
		for(int i : order.getDfsOrder(G.reverse())){
			if(this.marked[i]) continue;
			dfs(G,i);
			count++;
		}
	}
	
	private void dfs(DiGraph G, int s){
		this.marked[s] = true;
		this.index[s] = count;
		for(int i : G.adj(s)){
			if(this.marked[i]) continue;
			this.marked[i] = true;
			this.index[i] = count;
			dfs(G,i);
		}
	}
	
	public boolean connected(int s, int d){
		return this.index[s] == this.index[d];
	}
	
	public int index(int s){
		return this.index[s];
	}
	
	public int noOfComponents(){
		return count + 1;
	}
}
