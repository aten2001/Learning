package org.hystrix.learning;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * Example class to demo Graceful Degradation
 * @author gokulvanan.v
 *
 */
public class CommandHelloFailure extends HystrixCommand<String> {
	
	private String name;

	public CommandHelloFailure(String name){
		super(HystrixCommandGroupKey.Factory.asKey(("Sample Group")));
		this.name = name;
	}

	@Override
	protected String run() throws Exception {
		throw new RuntimeException();
	}
	
	//catch processing for all exception except HystrixBadRequestException 
	@Override
	public String getFallback(){
		return "Hello Failure " + name + "!";
	}
}
