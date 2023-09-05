package webserver;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

import com.sun.net.httpserver.HttpExchange;

public class SimpleHandler {
    HttpExchange exchange = null;
    public void handler(HttpExchange ex, String reqestAppName, String requestPage){
        this.exchange = ex;


    }

    public void sendResponseFile(int statusCode, File responseFile) throws IOException{
        String encoding = "UTF-8";
        String fileName = responseFile.getName();
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);

        exchange.getResponseHeaders().set("Content-Type", "text/"+ extension +"; charset=" + encoding);
        exchange.sendResponseHeaders(200, responseFile.length());

        OutputStream outputStream = exchange.getResponseBody();
        Files.copy(responseFile.toPath(), outputStream);
        outputStream.close();
    }
}
