package org.learning.jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;

public class OneConnector {
	
	public static void main( String[] args ) throws Exception
    {
        // The Server
        Server server = new Server();
 
        // HTTP connector
        ServerConnector http = new ServerConnector(server); //note more than one connector can be attache to server
        // connector abstracts - protocl
        // by default ServerConnector - HTTP protocol
        // by protocol I mean 3 way hanshake and the others
        
        http.setHost("localhost");
        http.setPort(8080);
        http.setIdleTimeout(30000); // idle period after which it timesout
 
        // Set the connector
        server.addConnector(http);
 
        // Set a handler
        server.setHandler(new HelloHandler());
 
        // Start the server
        server.start();
        server.join();
    }
}
