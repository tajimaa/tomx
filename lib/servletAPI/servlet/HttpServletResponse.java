package servletAPI.servlet;

import java.io.IOException;
import java.io.PrintWriter;

public interface HttpServletResponse {
    void setContentType(String contentType);
    void setCharacterEncoding(String charset);
    void sendRedirect(String url) throws IOException;
    PrintWriter getWriter() throws IOException;
}
