package servletAPI.servletimple;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLClassLoader;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;

import servletAPI.servlet.HttpServlet;
import servletAPI.servlet.HttpServletRequest;
import util.CreateDynamicPath;

public class ServletService {
    private static HttpServlet createServlet(ServletInfo info) throws Exception{
        String filePath = CreateDynamicPath.getPath("\\webapps\\"+info.webAppName+"\\WEB-INF\\classes\\");
        URL servletUrl = new File(filePath).toURI().toURL();
        URLClassLoader loader = URLClassLoader.newInstance(new URL[]{servletUrl});
        
        Class<?> clazz;
        if(info.servletDirectory.isEmpty()) {
            clazz = loader.loadClass(info.servletClassName);
        } else {
            clazz = loader.loadClass(info.servletDirectory+"."+info.servletClassName);
        }
        return (HttpServlet) clazz.newInstance();
    }

    private static Map<String, String> queryToMap(String query) {
        Map<String, String> parameterMap = new HashMap<String, String>();
        if(query != null){
            String[] splitQuery = query.split("\\&");
            for(String str: splitQuery) {
                String[] keyValue = str.split("\\=");
                parameterMap.put(keyValue[0],keyValue[1]);
            }
        }
        return parameterMap;
    }

    public static void doService(HttpExchange exchange, ServletInfo info) throws Exception {
        if(info.servelt==null) {
            info.servelt = createServlet(info);
        }
        HttpServletRequest request = null;
        if(exchange.getRequestMethod().equals("GET")) {
            Map<String,String> map;
            map = queryToMap(exchange.getRequestURI().getQuery());
            request = new HttpServletRequestImpl(exchange.getRequestMethod(), map);

        } else if(exchange.getRequestMethod().equals("POST")) {
            InputStreamReader in = new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(in);
            StringBuilder requestBody = new StringBuilder();
            String str;

            while((str = reader.readLine()) != null) {
                requestBody.append(str);
            }
            reader.close();
            Map<String,String> map;
            map = queryToMap(requestBody.toString());
            request = new HttpServletRequestImpl(exchange.getRequestMethod(), map);
        }
        HttpServletResponseImple response = new HttpServletResponseImple(exchange);
        info.servelt.service(request, response);
        response.finishResponse();
    }
}
