package graphs;

import java.util.Stack;


public class DFSOrder {
	
	private boolean[] marked = null;
	private int [] from = null;
	
	public Iterable<Integer> getDfsOrder(DiGraph G){
		int N = G.V();
		Stack<Integer> output = new Stack<Integer>();
		Stack<Integer> stack  = new Stack<>();
		this.marked = new boolean[N];
		this.from = new int[N];
		
		for(int i=0; i<N; i++){
			if(!this.marked[i]) {
				this.marked[i] = true;
				this.from[i] = 0;
				dfs(G,i,output);
				output.push(i);
				
			}
		}
		return output;
	}
	
	private void dfs(DiGraph G, int s, Stack<Integer> stack){
		for(int i : G.adj(s)){
			if(this.marked[i]) continue;
			this.marked[i] = true;
			this.from[i] = s;
			dfs(G,i,stack);
			stack.push(i);
		}	
	}

}
