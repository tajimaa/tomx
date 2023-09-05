package webserver;

import java.io.IOException;

import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

import servletAPI.servletimple.ServletInfo;

public class TomServer {
    public static void main(String[] args) throws IOException{
        ServletInfo.setupServletInfo();

        int webServerPort = 8080;
        int shutdownServerPort = 8001;
        String shutdownCommand = "shutdown";
        int shutdownCount = 9;

        new Thread(()->{
            try{
                HttpServer webServer = HttpServer.create(new InetSocketAddress(webServerPort), 0);
                webServer.createContext("/", new TomHandler());
                System.out.println("server started >>>>");
                webServer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try{
                HttpServer shutdownServer = HttpServer.create(new InetSocketAddress(shutdownServerPort), 0);
                shutdownServer.createContext("/", exchange -> {
                    if (exchange.getRequestMethod().equalsIgnoreCase("POST")){
                        if (exchange.getRequestURI().getPath().equalsIgnoreCase("/" + shutdownCommand)) {
                            System.out.println("サーバーがシャットダウンします");
                            System.out.print("server shutdown:  ");
                            for(int i=shutdownCount; i>=0; i--){
                                System.out.print("\b" + i + "s");
                                try{
                                    Thread.sleep(1000);
                                    System.out.print("\b");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            System.exit(0);
                        }
                    }
                });
                shutdownServer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}