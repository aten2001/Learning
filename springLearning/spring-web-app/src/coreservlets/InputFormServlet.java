package coreservlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.springframework.context.*;
import org.springframework.web.context.support.*;

public class InputFormServlet extends HttpServlet {
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
    ApplicationContext context =
      WebApplicationContextUtils.getRequiredWebApplicationContext(
                                   (getServletContext()));
    context.getBean("colorPreferences"); 
    String address = "/WEB-INF/jsp/input-form.jsp";
    RequestDispatcher dispatcher =
      request.getRequestDispatcher(address);
    dispatcher.forward(request, response);
  }
}
