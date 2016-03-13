package graphs;

import java.util.LinkedList;
import java.util.List;


public class DiGraph {

	public List<Integer>[] vertices;
	public final int V;
	public int E;
	
	@SuppressWarnings("unchecked")
	public DiGraph(int V){
		this.V = V;
		this.vertices = new LinkedList[V];
		for(int i=0; i<V; i++) this.vertices[i] = new LinkedList<>();
		this.E=0;
	}
	
	public Iterable<Integer> adj(int v){
		return this.vertices[v];
	}
	
	public void addEdge(int s, int d){
		this.vertices[s].add(d);
	}
	
	public Integer V(){
		return V;
	}
	
	public Integer E(){
		return E;
	}
	
	public DiGraph reverse(){
		DiGraph rev = new DiGraph(this.V);
		for(int i=0; i<V; i++){
			for(int j : this.vertices[i]){
				rev.addEdge(j, i);
			}
		}
		return rev;
	}
}
