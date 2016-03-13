package org.hystrix.learning;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * Basic Hystrix Command that returns a hardcode String + name of the command
 * @author gokulvanan.v
 *
 */
public class CommandHelloWorld extends HystrixCommand<String>{

	private final String name; //these variables should be immutable 
	//as run method would be executed by a pool of threads
	//Mandatory that every command should have a groupName
	public CommandHelloWorld(String name){
		super(HystrixCommandGroupKey.Factory.asKey("SampleGroup"));
		this.name = name;
	}

	@Override
	protected String run() throws Exception {
		return "Hello "+name+"!";
	}
}
