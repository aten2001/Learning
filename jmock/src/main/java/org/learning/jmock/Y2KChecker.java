package org.learning.jmock;

import java.util.Date;
import java.util.Calendar;

public class Y2KChecker {
	
	public static class Clock {
		public long currentTimeMillis(){
			return System.currentTimeMillis();
		}
	}
	
	private final Clock clock;
	
	public Y2KChecker(Clock clk){
		this.clock=clk;
	}
	
	public void check() throws Y2KException{
		Calendar cal = Calendar.getInstance();
		cal.set(2000,1,1);
		
		Date date = cal.getTime();
		
		if(date.getTime() == clock.currentTimeMillis()){
			throw new Y2KException("Y2K Bug");
		}
		
	}
	
	public static class Y2KException extends Exception {
		public Y2KException(String reason){
			super(reason);
		}
	}

}
