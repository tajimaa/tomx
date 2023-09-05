package util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class WebXmlReader {
    public static Map<String, List<String>> getWebXML(String fullpath) {//index0 = servlet-class, index1 = url-pattern
        Map<String, List<String>> webxmlmap = new HashMap<>();
        
        try {
            File webXmlFile = new File(fullpath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(webXmlFile);

            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("servlet");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String servletName = element.getElementsByTagName("servlet-name").item(0).getTextContent();
                    String servletClass = element.getElementsByTagName("servlet-class").item(0).getTextContent();

                    webxmlmap.put(servletName, new ArrayList<>());
                    webxmlmap.get(servletName).add(servletClass);
                }
            }

            NodeList servletMappingNodeList = doc.getElementsByTagName("servlet-mapping");
            for(int i=0; i < servletMappingNodeList.getLength(); i++) {
                Node node = servletMappingNodeList.item(i);
                if(node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String servletName = element.getElementsByTagName("servlet-name").item(0).getTextContent();
                    String urlPattern = element.getElementsByTagName("url-pattern").item(0).getTextContent();

                    if(webxmlmap.containsKey(servletName)) {
                        webxmlmap.get(servletName).add(urlPattern);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return webxmlmap;
    }
}
