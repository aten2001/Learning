package coreservlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.springframework.context.*;
import org.springframework.web.context.support.*;

public class CustomerServlet extends HttpServlet {
  public void doPost(HttpServletRequest request,
                     HttpServletResponse response)
      throws ServletException, IOException {
    ApplicationContext context =
      WebApplicationContextUtils.getRequiredWebApplicationContext(
                                   (getServletContext()));
    CustomerLookupService lookupService =
      (CustomerLookupService)context.getBean("sampleLookupService");
    String id = request.getParameter("cust-id");
    String address;
    if (isEmpty(id)) {
      address = "missing-id.jsp";
    } else {
      Customer customer = lookupService.getCustomer(id);
      if (customer == null) {
        request.setAttribute("id", id);
        address = "invalid-id.jsp";
      } else {
        request.setAttribute("customer", customer);
        address = "show-customer.jsp";
      }
    }
    address = "/WEB-INF/jsp/" + address;
    ColorPreferences colorPreferences =
      (ColorPreferences)context.getBean("colorPreferences");
    colorPreferences.setForeground(request.getParameter("fg"));
    colorPreferences.setBackground(request.getParameter("bg"));
    RequestDispatcher dispatcher =
      request.getRequestDispatcher(address);
    dispatcher.forward(request, response);
  }
  
  private boolean isEmpty(String value) {
    return((value == null) || (value.trim().equals("")));
  }
}
