package org.hystrix.learning;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;

public class CommandWithNetworkFallback extends HystrixCommand<String> {
	
	private final int id;
	
	public CommandWithNetworkFallback(int id){
		super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("RemoteService"))
				.andCommandKey(HystrixCommandKey.Factory.asKey("GetValueCommand")));
		this.id = id;
	}

	@Override
	protected String run() throws Exception {
		throw new RuntimeException("Force Failure");
	}
	
	@Override
	protected String getFallback(){
		return new FallBackCommand(id).execute();
	}

}
