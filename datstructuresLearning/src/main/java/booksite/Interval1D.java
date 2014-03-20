package booksite;

public class Interval1D {

	private double left, right;
	
	public boolean intersects(Interval1D that){
		if(this.right < that.left) return false;
		if(that.right < this.left) return false;
		return true;
	}
	
	public boolean contains(int val){
		return (this.left < val && this.right > val);
	}
}
