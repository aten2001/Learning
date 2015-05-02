package org.hystrix.learning;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * Note as the name suggest this is per request cache
 * Use case - where you make multiple commands in one transaction request scope.. 
 * and when something fails you may want to remake the request and the cache
 * scoops up rather than making the same call 
 * @author gokulvanan.v
 *
 */
public class CommandRequestCache extends HystrixCommand<Boolean> {

	private String name;
	public CommandRequestCache(String name){
		super(HystrixCommandGroupKey.Factory.asKey("test"));
		this.name = name;
	}
	
	
	@Override
	protected Boolean run() throws Exception {
//		Thread.sleep(100);// time consuming task
		return "gokul".equals(name);
	}
	
	@Override
	protected String getCacheKey(){
		return name;
	}
	
}
