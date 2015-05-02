package org.hystrix.learning;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;

public class PrimaryCommand extends HystrixCommand<String> {

	private final int id;
	
	public PrimaryCommand(int id){
		super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("SystemX"))
				.andCommandKey(HystrixCommandKey.Factory.asKey("PrimaryCommand"))
				.andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("PrimaryCommand"))
				//600ms time out 
				.andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
						.withExecutionIsolationThreadTimeoutInMilliseconds(600)));
        this.id = id;
    }
	
	@Override
	protected String run() throws Exception{
		//timetaking task here
		return "primary"+id;
	}
}
