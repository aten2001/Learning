package org.hystrix.learning;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixRequestCache;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;

public class GetCommand extends HystrixCommand<String> {

	public static volatile String fld = "test"; //mutable to simulate data store
	private final int id;
	
	public enum CommandKey implements HystrixCommandKey{
		GET
	}
	
	public GetCommand(int id){
		super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("group"))
				.andCommandKey(CommandKey.GET));
		this.id = id;
	}
	
	@Override
	public String run(){
		return fld+id;
	}
	
	@Override
	public String getCacheKey(){
		return String.valueOf(this.id);
	}

	public static void flushCache(int id) {
		HystrixRequestCache.getInstance(CommandKey.GET,
				HystrixConcurrencyStrategyDefault.getInstance()).clear(String.valueOf(id));
	}
	
}
