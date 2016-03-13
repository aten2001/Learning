package org.hystrix.learning;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;

public class SecondaryCommand extends HystrixCommand<String> {

	private final int id;
	
	public SecondaryCommand(int id){
		super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("SystemX"))
				.andCommandKey(HystrixCommandKey.Factory.asKey("SecondaryCommand"))
				.andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("Secondary"))
				//600ms time out 
				.andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
						.withExecutionIsolationThreadTimeoutInMilliseconds(100)));
		this.id = id;
	}

	@Override
	protected String run() throws Exception {
		return "secondary"+id;
	}
	
}
