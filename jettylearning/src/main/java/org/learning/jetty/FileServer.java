package org.learning.jetty;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;

/**
 * Example to serve file
 * @author gokulvanan.v
 *
 */
public class FileServer {
	public static void main(String[] args) throws Exception
    {
        Server server = new Server(8080);
 
        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setDirectoriesListed(true);
        resource_handler.setWelcomeFiles(new String[]{ "index.html" });
        resource_handler.setResourceBase(".");
 
        // Add the ResourceHandler to the server.
        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[] { resource_handler, new DefaultHandler() }); //chained
        // first request goes to resource_handler if its path is index.html is servers response and thats the end of it
        // else it goes to DefaultHanlder which serves 404
        server.setHandler(handlers);
 
        server.start();
        server.join(); // calls Thread.join() on server thread -- hence main thread waits for server thread to die
    }
}
