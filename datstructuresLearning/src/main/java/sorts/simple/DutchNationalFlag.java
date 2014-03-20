package sorts.simple;

/**
 * Dutch national flag. Given an array of N buckets, each containing a red, white,
 *  or blue pebble, sort them by color. The allowed operations are:
swap(i,j): swap the pebble in bucket i with the pebble in bucket j.
color(i): color of pebble in bucket i.
The performance requirements are as follows:
At most N calls to color().
At most N calls to swap().
Constant extra space.
 * @author gokulvanan
 *
 */
public class DutchNationalFlag {
	public static final int RED = 1;
	public static final int WHITE = 2;
	public static final int BLUE = 3;
	
	private Integer[] data;
	
	public Integer color(int i){
		return data[i];
	}
	
	public void swap(int i, int j){
		int temp = data[i];
		data[i] = data[j];
		data[j] = temp;
	}
	
	public void sort(){
		
	}
}
