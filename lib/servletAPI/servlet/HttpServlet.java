package servletAPI.servlet;

public class HttpServlet {
    protected void doGet(HttpServletRequest reqest, HttpServletResponse response) throws ServletException, java.io.IOException {}

    protected void doPost(HttpServletRequest reqest, HttpServletResponse response) throws ServletException, java.io.IOException {}

    public void service(HttpServletRequest reqest, HttpServletResponse response) throws ServletException, java.io.IOException {

        if (reqest.getMethod().equals("GET")) {
            doGet(reqest, response);
        } else if (reqest.getMethod().equals("POST")) {
            doPost(reqest, response);
        }
    }
}
