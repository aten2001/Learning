package org.hystrix.learning;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixThreadPoolKey;

public class CommandCustomNameThreadPool  extends HystrixCommand<String>{

	public enum GroupKeyName implements HystrixCommandGroupKey{
		SAMPLE_GROUP
	}
	protected CommandCustomNameThreadPool(
			String name) {
		super(Setter.withGroupKey(GroupKeyName.SAMPLE_GROUP)
				.andCommandKey(HystrixCommandKey.Factory.asKey("CommandName"))
//				.andCommandPropertiesDefaults()
				.andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("threadPoolName"))
//				.andThreadPoolPropertiesDefaults()
				);
	}

	@Override
	protected String run() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
