package booksite;

public class Vector {

	private int N;
	private double [] data;
	
	public Vector(double [] val){
		this.N = val.length;
		this.data = new double[N];
		for(int i=0; i<val.length; i++) this.data[i] = val[i];
	}
	
	private double dotSum(Vector that){
		double sum =0.0D;
		for(int i=0; i<N; i++) sum += that.data[i] * this.data[i];
		return sum;
	}
	
	public double magnitude(){
		return Math.sqrt(this.dotSum(this));
	}
	
	public double distnaceTo(Vector that){
		return this.minus(that).magnitude();
	}
	
	public Vector plus(Vector that){
		double [] buff = new double[N];
		for(int i=0; i<N; i++) buff[i] = that.data[i] + this.data[i];
		return new Vector(buff);
	}
	
	public Vector minus(Vector that){
		double [] buff = new double[N];
		for(int i=0; i<N; i++) buff[i] = this.data[i] - that.data[i];
		return new Vector(buff);
	}
	
	public Vector scale(double val){
		double [] buff = new double[N];
		for(int i=0; i<N; i++) buff[i] = this.data[i] * val;
		return new Vector(buff);
	}
}
