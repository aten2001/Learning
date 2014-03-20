package unionFind;

public class Percolation {

	private WeightedQuickUnionUF uf = null;
	private WeightedQuickUnionUF fullUF = null; // to check is FULL
	boolean open[] = null;
	private int N = 0;

	public Percolation(int N){
		int sz = N*N + 2;
		this.uf = new WeightedQuickUnionUF(sz);
		this.fullUF = new WeightedQuickUnionUF(sz);
		this.N=N;
		this.open=new boolean[sz];
		this.open[0]=true;
		this.open[N*N +1] = true;
	}

	public void open(int i, int j){
		if(isOpen(i, j)) return;
		int a = convert(i, j);
		this.open[a] = true;
		if(i == 1){
			this.uf.union(0, a);// connect to top
			this.fullUF.union(0, a);
		}
		if(i == N) this.uf.union(0, N*N+1); // connect to bottom
		for(int z=-1; z<=1; z+=2){
			try{
				this.uf.union(a, convert(i+z, j));
				this.uf.union(a, convert(i, j+z));
			}catch(IndexOutOfBoundsException e){
				continue;
			}
		}
		
	}

	public boolean isOpen(int i, int j){
		return this.open[convert(i, j)];
	}

	public boolean isFull(int i, int j){
		int v = convert(i, j);
		if(this.open[v]){
			return this.fullUF.connected(v, 0);
		}
		return false;
	}

	public boolean percolates(){
		return this.uf.connected(0, (N*N+1));
	}

	private int convert(int i, int j){
		if(i < 1 || j < 1 || i > N || j > N) throw new IndexOutOfBoundsException();
		return (--i * N) + (--j) + 1; // +1 added as 0 and N^2 would stand for top and bottom
	}
}
