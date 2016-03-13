package booksite;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.ThreadMXBean;

public class StopWatchCPU {

	private final ThreadMXBean threadTimer;
	private final long start;
	private static final double NANOSECONDS_PER_SECOND = 1000000000;
	
	public StopWatchCPU(){
		this.threadTimer = ManagementFactory.getThreadMXBean();
		this.start = threadTimer.getCurrentThreadCpuTime();
	}
	
	public double duration(){
		long val = threadTimer.getCurrentThreadCpuTime();
		return (val - start)/NANOSECONDS_PER_SECOND;
	}
}
