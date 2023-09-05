package servletAPI.servletimple;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import com.sun.net.httpserver.HttpExchange;

import servletAPI.servlet.HttpServletResponse;

public class HttpServletResponseImple implements HttpServletResponse {
    PrintWriter printWriter;
    HttpExchange exchange;
    private String contentType = "application/octet-stream";
    private String characterEncoding = "Windowds-31J";

    public void setContentType(String contentType) {
        this.contentType = contentType;
        String[] temp = contentType.split(" *;");
        if (temp.length > 1) {
            String[] keyValue = temp[1].split("=");
            if (keyValue.length == 2 && keyValue[0].equals("charset")) {
                setCharacterEncoding(keyValue[1]);
            }
        }
    }

    public void setCharacterEncoding(String charset) {
        this.characterEncoding = charset;
    }

    public PrintWriter getWriter() throws IOException {
        int responseCode = 200;
        
        exchange.getResponseHeaders().set("Content-Type", contentType);
        exchange.getResponseHeaders().set("Connection", "close");
        exchange.getResponseHeaders().set("Custom-Header", "Custom Value");
        exchange.sendResponseHeaders(responseCode, 0);
   
        printWriter = new PrintWriter(new OutputStreamWriter(exchange.getResponseBody(), characterEncoding));
        return printWriter;
    }

    public void sendRedirect(String url) throws IOException{
        int responseCode = 301;
        exchange.getResponseHeaders().set("Location", url);
        exchange.sendResponseHeaders(responseCode, 0);
    }

    public void finishResponse() throws IOException {
        if (printWriter != null) {
            printWriter.flush();
            printWriter.close();
        }
        exchange.getResponseBody().close();
    }

    public HttpServletResponseImple(HttpExchange exchange) throws IOException {
        this.exchange = exchange;
    }
}

