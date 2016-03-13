package org.learning.instrumentation;

import java.lang.instrument.Instrumentation;

/**
 * Agent class
 *
 */
public class Agent 
{
    public static void premain( String agentArgs, Instrumentation inst )
    {
    	inst.addTransformer(new MyTransformer());
    }
}
