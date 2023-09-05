package ttc;

import java.io.IOException;
import java.io.PrintWriter;

import tomx.servletAPI.HttpServlet;
import tomx.servletAPI.HttpServletRequest;
import tomx.servletAPI.HttpServletResponse;
import tomx.servletAPI.ServletException;

public class MyServlet2 extends HttpServlet{
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html; charset=UTF-8");
        String method = req.getMethod();
        String name = req.getParameter("name");
        out.println("<html>");
        out.println("<head><title>myservlet222222222222222222222</title></head>");
        out.println("<body>");
        out.println("<h1>MyServlet22222222222222</h1>");
        out.println("<p>myservlet222222222222222</p>");
        out.println("<p>"+method+"</p>");
        out.println("<p>"+name+"</p>");
        out.println("</body>");
        out.println("</html>");
        System.out.println(("servlet2: hello!"));
    }
}
