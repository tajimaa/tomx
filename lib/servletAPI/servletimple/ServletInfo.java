package servletAPI.servletimple;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import servletAPI.servlet.HttpServlet;
import util.CreateDynamicPath;
import util.WebXmlReader;

public class ServletInfo {
    
    public static Map<String, Map<String, ServletInfo>> webapps = new HashMap<>();

    public String webAppName;
    public String urlPattern;
    public String servletDirectory;
    public String servletClassName;
    public HttpServlet servelt;

    public static void setupServletInfo() {
        String webappsPath  = CreateDynamicPath.getPath("\\webapps");
        File webappsDirectory = new File(webappsPath);
        File[] webapp = webappsDirectory.listFiles();

        for(File file : webapp) {
            String webappName = file.getName();
            Map<String, ServletInfo> servlets = new HashMap<>();
            Map<String,List<String>> xmlmap = new HashMap<>();
            xmlmap = WebXmlReader.getWebXML(webappsPath+"\\"+webappName+"\\WEB-INF\\web.xml");
            
            System.out.println("~~~~~~~~~~~~~~~~~~~~~ "+webappName+" ~~~~~~~~~~~~~~~~~~~~~~~~~");
            for(String key: xmlmap.keySet()) {
                String urlPattern = xmlmap.get(key).get(1);
                String servletClass = xmlmap.get(key).get(0);
                String temp[] = servletClass.split("\\.");
                StringBuilder servletDirectory = new StringBuilder();
                for (int i = 0; i < temp.length - 1; i++) {
                    servletDirectory.append(temp[i]);
                    if (i < temp.length - 2) {
                        servletDirectory.append("\\");
                    }
                }
                System.out.println();
                System.out.println("servletClass: "+servletClass);
                String temp2[] = servletClass.split("\\.");
                String servletClassName = temp2[temp2.length-1];
                System.out.println("urlPattern: "+ urlPattern);
                System.out.println("servletDirectory: "+ servletDirectory);
                System.out.println("servletClassName: "+ servletClassName);
                servlets.put(urlPattern, new ServletInfo(webappName, urlPattern, servletDirectory.toString(), servletClassName, null));
            }
            webapps.put(webappName, servlets);
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public ServletInfo(String webAppName, String urlPattern, String servletDirectory, String servletClassName, HttpServlet servlet) {
        this.webAppName = webAppName;
        this.urlPattern = urlPattern;
        this.servletDirectory = servletDirectory;
        this.servletClassName = servletClassName;
        this.servelt = servlet;
    }

    public static ServletInfo searchServlet(String webappName,String urlPattern) {
        if(webapps.containsKey(webappName)){
            if(webapps.get(webappName).containsKey(urlPattern)){
                return webapps.get(webappName).get(urlPattern);
            }else {
                return null;
            }
        } else {
            return null;
        }
    }
}