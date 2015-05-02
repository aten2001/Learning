package org.hystrix.learning;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

/**
 * Unit test for simple Command.
 */
public class CommandRequestCacheTest extends TestCase
{
	public CommandRequestCacheTest( String testName )
	{
		super( testName );
	}

	public static Test suite()
	{
		return new TestSuite( CommandRequestCacheTest.class );
	}

	public void testWithoutCacheHits() {
		HystrixRequestContext context = HystrixRequestContext.initializeContext();
		try {
			Long start = System.currentTimeMillis();
			assertTrue(new CommandRequestCache("gokul").execute());
			System.out.println(System.currentTimeMillis()-start);
			
			start = System.currentTimeMillis();
			assertTrue(new CommandRequestCache("gokul").execute());
			System.out.println(System.currentTimeMillis()-start);
			assertTrue(new CommandRequestCache("gokul").execute());
			assertFalse(new CommandRequestCache("test").execute());
			assertFalse(new CommandRequestCache("sdfds").execute());
			assertFalse(new CommandRequestCache("58672").execute());
		} finally {
			context.shutdown();
		}
	}
	
	 public void testWithCacheHits() {
         HystrixRequestContext context = HystrixRequestContext.initializeContext();
         try {
             CommandRequestCache command2a = new CommandRequestCache("gokul");
             CommandRequestCache command2b = new CommandRequestCache("gokul");

             assertTrue(command2a.execute());
             // this is the first time we've executed this command with
             // the value of "2" so it should not be from cache
             assertFalse(command2a.isResponseFromCache());

             assertTrue(command2b.execute());
             // this is the second time we've executed this command with
             // the same value so it should return from cache
             assertTrue(command2b.isResponseFromCache());
         } finally {
             context.shutdown();
         }

         // start a new request context
         context = HystrixRequestContext.initializeContext();
         try {
             CommandRequestCache command3b = new CommandRequestCache("gokul");
             assertTrue(command3b.execute());
             // this is a new request context so this 
             // should not come from cache
             assertFalse(command3b.isResponseFromCache());
         } finally {
             context.shutdown();
         }
     }
}
