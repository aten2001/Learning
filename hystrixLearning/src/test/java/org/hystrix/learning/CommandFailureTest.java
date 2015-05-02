package org.hystrix.learning;

import java.util.concurrent.Future;

import rx.Observable;
import rx.Observer;
import rx.functions.Action1;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple Command.
 */
public class CommandFailureTest extends TestCase
{
	public CommandFailureTest( String testName )
	{
		super( testName );
	}

	public static Test suite()
	{
		return new TestSuite( CommandFailureTest.class );
	}

	/**
	 * Synchronous execution of CommandHelloFailure
	 */
    public void testSynchronous() {
        assertEquals("Hello Failure World!", new CommandHelloFailure("World").execute());
        assertEquals("Hello Failure Bob!", new CommandHelloFailure("Bob").execute());
    }

	
}
