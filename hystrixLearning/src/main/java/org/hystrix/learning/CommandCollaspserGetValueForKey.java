package org.hystrix.learning;

import java.util.Collection;
import java.util.List;

import com.netflix.hystrix.HystrixCollapser;
import com.netflix.hystrix.HystrixCommand;

/**
 * HyStrixCollapser <T,J,K>
 * T - batch response data type in this case List<String>
 * J - response type - String
 * K - requestArgs type - Integer
 * @author gokulvanan.v
 *
 */
public class CommandCollaspserGetValueForKey extends HystrixCollapser<List<String>, String, Integer>{

	private final Integer key;
	
	public CommandCollaspserGetValueForKey(Integer key){
		this.key = key;
	}

	/**
	 * Returns HystrxiCommand that takes list of Request Map and returns List of Response
	 * note request is map <T,J> where T = response J = request.. and initaily 
	 * response are null; Post command execution and call to mapResponseToRequest method
	 * which is below does this response get filled
	 */
	@Override
	protected HystrixCommand<List<String>> createCommand(
			Collection<com.netflix.hystrix.HystrixCollapser.CollapsedRequest<String, Integer>> requests) {
		return new BatchCommand(requests);
	}

	@Override
	public Integer getRequestArgument() {
		return key;
	}

	@Override
	protected void mapResponseToRequests(
			List<String> batchResponse,
			Collection<com.netflix.hystrix.HystrixCollapser.CollapsedRequest<String, Integer>> requests) {
		int count = 0;
		for(CollapsedRequest<String, Integer> req: requests){
			req.setResponse(batchResponse.get(count++)); //maps resonse to request map
		}
		
	}
	
	
}
