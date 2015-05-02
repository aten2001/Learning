package org.learning.jetty;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

public class HelloHandler extends AbstractHandler
{
    final String greeting;
    final String body;
 
    public HelloHandler()
    {
        this("Hello World");
    }
 
    public HelloHandler( String greeting )
    {
        this(greeting, null);
    }
 
    public HelloHandler( String greeting, String body )
    {
        this.greeting = greeting;
        this.body = body;
    }
 
    public void handle( String target, //URI or NAME From Named dispatcher
                        Request baseRequest, //jetty mutable request object
                        HttpServletRequest request, //servletRequest
                        HttpServletResponse response // servletResponse
                        ) throws IOException,ServletException
    {
        response.setContentType("text/html; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
 
        PrintWriter out = response.getWriter();
 
        out.println("<h1>" + greeting + "</h1>");
        if (body != null)
        {
            out.println(body);
        }
 
        baseRequest.setHandled(true);
    }
}
