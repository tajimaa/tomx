package util;

import java.nio.file.Path;
import java.nio.file.Paths;


public class CreateDynamicPath {
    public static String getPath(String appendPath)  {
        StringBuilder rootPath = new StringBuilder();
        String rootDirectory = "tomx";

        try {
            Class<?> clazz = CreateDynamicPath.class;
            Path classFilePath = Paths.get(clazz.getResource(clazz.getSimpleName() + ".class").toURI());
            rootPath.append(classFilePath.getParent().toString());
            rootPath.delete(rootPath.indexOf(rootDirectory, 0) + rootDirectory.length(), rootPath.length());
            if (appendPath != null && !appendPath.startsWith("\\")) {
                rootPath.append("\\");
            }
            rootPath.append(appendPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rootPath.toString();
    }
}
