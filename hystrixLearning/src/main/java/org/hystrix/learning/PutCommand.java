package org.hystrix.learning;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class PutCommand extends HystrixCommand<String> {

	private final int id;
	private final String value;
	
	public PutCommand(int id, String value){
		super(HystrixCommandGroupKey.Factory.asKey("group"));
		this.id = id;
		this.value = value;
	}

	@Override
	protected String run() throws Exception {
		GetCommand.fld = value; // update datastore
		GetCommand.flushCache(id);
		return null;
	}
}
