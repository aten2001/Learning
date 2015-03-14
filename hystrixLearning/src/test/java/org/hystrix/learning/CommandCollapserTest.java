package org.hystrix.learning;

import java.util.concurrent.Future;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixEventType;
import com.netflix.hystrix.HystrixRequestLog;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class CommandCollapserTest  extends TestCase{

	public CommandCollapserTest( String testName )
	{
		super( testName );
	}

	public static Test suite()
	{
		return new TestSuite( CommandRequestCacheTest.class );
	}

	/**
	 * Awesome way to use HystrixCollapser to build commands which abstract the performance batching
	 * they do in the backend
	 * @throws Exception
	 */
	public void testCollapser() throws Exception {
	    HystrixRequestContext context = HystrixRequestContext.initializeContext();
	    //very important.. to start context as all mapping are specific to this context
	    try {
	        Future<String> f1 = new CommandCollaspserGetValueForKey(1).queue();
	        Future<String> f2 = new CommandCollaspserGetValueForKey(2).queue();
	        Future<String> f3 = new CommandCollaspserGetValueForKey(3).queue();
	       

	        assertEquals("ValueForKey: 1", f1.get()); //batch fires
	        Future<String> f4 = new CommandCollaspserGetValueForKey(4).queue();
	        assertEquals("ValueForKey: 2", f2.get()); //from batch output
	        assertEquals("ValueForKey: 3", f3.get()); // from batch output
	        assertEquals("ValueForKey: 4", f4.get()); // new call made and output returned

	        // assert that the batch command 'GetValueForKey' was in fact
	        // executed and that it executed only once
	        assertEquals(2, HystrixRequestLog.getCurrentRequest().getExecutedCommands().size());
	        HystrixCommand<?> command = HystrixRequestLog.getCurrentRequest().getExecutedCommands().toArray(new HystrixCommand<?>[1])[0];
	        // assert the command is the one we're expecting
	        assertEquals("GetValueForKey", command.getCommandKey().name());
	        // confirm that it was a COLLAPSED command execution
	        assertTrue(command.getExecutionEvents().contains(HystrixEventType.COLLAPSED));
	        // and that it was successful
	        assertTrue(command.getExecutionEvents().contains(HystrixEventType.SUCCESS));
	    } finally {
	        context.shutdown();
	    }
	}
}
