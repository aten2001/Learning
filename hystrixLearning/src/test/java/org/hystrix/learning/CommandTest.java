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
public class CommandTest extends TestCase
{
	public CommandTest( String testName )
	{
		super( testName );
	}

	public static Test suite()
	{
		return new TestSuite( CommandTest.class );
	}

	/**
	 * Synchronus execution of CommandHelloWorld
	 */
	public void testSyncCommandHelloWorld()
	{
		assertEquals("Hello World!", new CommandHelloWorld("World").execute());
		assertEquals("Hello Bob!", new CommandHelloWorld("Bob").execute());
	}

	public void testAsynchronous1() throws Exception {
		assertEquals("Hello World!", new CommandHelloWorld("World").queue().get());
		assertEquals("Hello Bob!", new CommandHelloWorld("Bob").queue().get());
	}

	public void testAsynchronous2() throws Exception {

		Future<String> fWorld = new CommandHelloWorld("World").queue();
		Future<String> fBob = new CommandHelloWorld("Bob").queue();

		assertEquals("Hello World!", fWorld.get());
		assertEquals("Hello Bob!", fBob.get());
	}

	public void testObservable() throws Exception {

		Observable<String> fWorld = new CommandHelloWorld("World").observe();
		Observable<String> fBob = new CommandHelloWorld("Bob").observe();

		// blocking
		assertEquals("Hello World!", fWorld.toBlocking().single());
		assertEquals("Hello Bob!", fBob.toBlocking().single());

		// non-blocking 
		fWorld.subscribe(new Observer<String>() {

			@Override
			public void onCompleted() {

			}

			@Override
			public void onError(Throwable e) {
				e.printStackTrace();
			}

			@Override
			public void onNext(String v) {
				System.out.println("onNext: " + v);
			}

		});

		// non-blocking
		// - ignore errors and onCompleted signal
		fBob.subscribe(new Action1<String>() {

			@Override
			public void call(String v) {
				System.out.println("onNext: " + v);
			}

		});
	}
}
