package utils;

public class StopWatch {

	private long start = 0L;
	
	public StopWatch(){
		this.start=System.currentTimeMillis();
	}
	
	public long getDuration(){
		return System.currentTimeMillis() - this.start;
	}
	
	public static void main(String[] args){
		int ip = 17170432;
		System.out.println(ip >>> 16 & 0x00FF	);
	}
}
