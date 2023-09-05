package servletAPI.servlet;

import java.io.UnsupportedEncodingException;

public interface HttpServletRequest {
    String getMethod();
    String getParameter(String name);
    void setCharacterEncoding(String env) throws UnsupportedEncodingException;
}
