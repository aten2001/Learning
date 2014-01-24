package org.learning.instrumentation;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


public class AgentTest extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AgentTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AgentTest.class );
    }

    /**
     * Rigourous Test :-)
     * @throws InterruptedException 
     */
    public void testApp() throws InterruptedException
    {
    	DummyClass clazz = new DummyClass();
    	clazz.sampleMethod();
        assertTrue( true );
    }
    
}
