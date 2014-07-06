package booksite;

public class Point2D {

	private double x, y;
	
	public Point2D(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	
	public double radius (){
		return Math.sqrt(x*x + y* y);
	}
	
	public double theta(){
		return Math.atan2(y, x);
	}
	
	public int ccw(Point2D a, Point2D b, Point2D c){
		double area = (b.x - a.x) * (c.y -a.y) + (b.y - a.y) * (c.x - a.x);
		if(area > 0) return 1;
		if(area < 0) return -1;
		return 0;
	}
}
