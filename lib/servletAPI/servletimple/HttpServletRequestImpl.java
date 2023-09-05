package servletAPI.servletimple;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.Map;

import servletAPI.servlet.HttpServletRequest;

public class HttpServletRequestImpl implements HttpServletRequest {
    private String requestMethod;
    private String characterEncoding = "UTF-8";
    private Map<String, String> parameters;

    public String getMethod() {
        return this.requestMethod;
    }

    public String getParameter(String name) {
        String value = this.parameters.get(name);
        String decoded = null;
        try {
            decoded = URLDecoder.decode(value, this.characterEncoding);
        } catch (UnsupportedEncodingException ex) {
            throw new AssertionError(ex);
        }
        return decoded;
    }

    public void setCharacterEncoding(String env) throws UnsupportedEncodingException {
        if (!Charset.isSupported(env)) {
            throw new UnsupportedEncodingException("encoding: " + env);
        }
        this.characterEncoding = env;
    }

    HttpServletRequestImpl(String requestMethod, Map<String, String> parameters) {
        this.requestMethod = requestMethod;
        this.parameters = parameters;
    }
}
