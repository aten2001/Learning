package org.hystrix.learning;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.netflix.hystrix.HystrixCollapser.CollapsedRequest;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;

public class BatchCommand extends HystrixCommand<List<String>> {
	
	private final Collection<CollapsedRequest<String, Integer>> requests;

	protected BatchCommand(Collection<CollapsedRequest<String, Integer>> request) {
		super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("sampleBatch"))
				.andCommandKey(HystrixCommandKey.Factory.asKey("GetValueForKey")));
		this.requests = request;
	}

	@Override
	protected List<String> run() throws Exception {
		ArrayList<String> response  = new ArrayList<>(requests.size());
		for(CollapsedRequest<String, Integer> req: requests){
			response.add("valueForKey:"+ req.getArgument());
		}
		return response;
	}

}
