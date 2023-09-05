import java.io.IOException;
import java.io.PrintWriter;

import tomx.servletAPI.*;

public class MyServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();

        out.println("<html><body><h1>222222222222Hello, HttpServlet!</h1></body></html>");
    }
}