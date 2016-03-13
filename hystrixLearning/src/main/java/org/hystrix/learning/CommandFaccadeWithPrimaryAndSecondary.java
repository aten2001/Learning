package org.hystrix.learning;

import com.netflix.config.DynamicBooleanProperty;
import com.netflix.config.DynamicPropertyFactory;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixCommandProperties.ExecutionIsolationStrategy;

public class CommandFaccadeWithPrimaryAndSecondary extends HystrixCommand<String> {

	private final static DynamicBooleanProperty usePimary = 
			DynamicPropertyFactory.getInstance()
			.getBooleanProperty("primarySecondary.userPrimary", true);
	
	private final int id;
	
	public CommandFaccadeWithPrimaryAndSecondary(int id){
		super(Setter
				.withGroupKey(HystrixCommandGroupKey.Factory.asKey("SysmteX"))
				.andCommandKey(HystrixCommandKey.Factory.asKey("PrimarySecondary"))
				.andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
						.withExecutionIsolationStrategy(ExecutionIsolationStrategy.SEMAPHORE)));
		this.id = id;
	}

	@Override
	protected String run() throws Exception {
		if(usePimary.get()){
			return new PrimaryCommand(id).execute();
		}else{
			return new SecondaryCommand(id).execute();
		}
	}
}
