package org.learning.jetty;

import org.eclipse.jetty.server.Server;

/**
 * Simpler server with no handlers
 *
 */
public class SimpleServer 
{
    public static void main( String[] args ) throws Exception
    {
        Server server = new Server(8080);
        server.start();
        server.dumpStdErr();
        server.join();
    }
}
