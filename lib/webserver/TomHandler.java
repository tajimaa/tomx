package webserver;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import servletAPI.servletimple.ServletInfo;
import servletAPI.servletimple.ServletService;

public class TomHandler implements HttpHandler{
    public void handle(HttpExchange exchange) throws IOException {
        String URI = exchange.getRequestURI().getPath().substring(1);
        System.out.println(exchange.getRequestMethod()+": "+ URI);
        String splitURI[] = URI.split("/");
        StringBuilder webAppName = new StringBuilder();
        StringBuilder requesetUrlPattern = new StringBuilder();
        
        for(int i=0; i<splitURI.length; i++){
            if(i == 0){
                webAppName.append( splitURI[i]);
            } else {
                requesetUrlPattern.append("/");
                requesetUrlPattern.append(splitURI[i]);
            }
        }
        String appName = webAppName.toString();
        String urlPattern = requesetUrlPattern.toString();

        ServletInfo info = ServletInfo.searchServlet(appName, urlPattern);
        if(info == null) {
            SimpleHandler simplehandler = new SimpleHandler();
            simplehandler.handler(exchange, appName, urlPattern);

        } else {
            try{
                ServletService.doService(exchange, info);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
