package org.hystrix.learning;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.netflix.config.ConfigurationManager;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

/**
 * Unit test for simple Command.
 */
public class CommandPrimarySecondaryTest extends TestCase
{
	public CommandPrimarySecondaryTest( String testName )
	{
		super( testName );
	}

	public static Test suite()
	{
		return new TestSuite( CommandPrimarySecondaryTest.class );
	}

	public void testPrimary() {
		HystrixRequestContext context = HystrixRequestContext.initializeContext();
		try {
			ConfigurationManager.getConfigInstance().setProperty("primarySecondary.usePrimary", true);
			assertEquals("responseFromPrimary-20", new CommandFaccadeWithPrimaryAndSecondary(20).execute());
		} finally {
			context.shutdown();
			ConfigurationManager.getConfigInstance().clear();
		}
	}

	public void testSecondary() {
		HystrixRequestContext context = HystrixRequestContext.initializeContext();
		try {
			ConfigurationManager.getConfigInstance().setProperty("primarySecondary.usePrimary", false);
			assertEquals("responseFromSecondary-20", new CommandFaccadeWithPrimaryAndSecondary(20).execute());
		} finally {
			context.shutdown();
			ConfigurationManager.getConfigInstance().clear();
		}
	}
}