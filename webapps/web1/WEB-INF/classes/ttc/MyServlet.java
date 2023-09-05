package ttc;

import java.io.IOException;
import java.io.PrintWriter;

import servletAPI.servlet.*;


public class MyServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.sendRedirect("http://www.yahoo.co.jp");
        
    }
}