package org.hystrix.learning;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixThreadPoolKey;

public class FallBackCommand  extends HystrixCommand<String>{

	private final int id;
	
	public FallBackCommand(int id){
		super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("RemoteServiceX")) //same group as is precursor
				.andCommandKey(HystrixCommandKey.Factory.asKey("FallbackCommand"))
				//Important to add different thread pool key as its in same group as its precursor and we
				//dont want both the precursor and its fallback being in same pool
				.andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("FallBackPollForRemoteServiceX")));
		this.id = id;
	}

	@Override
	protected String run() throws Exception {
		//some mock that you know will not fail
		return "Mocked return Val";
	}
	
	
}
