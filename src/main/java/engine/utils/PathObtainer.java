package engine.utils;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathObtainer {

    public static Path getProperPath(String res){
        URL url = PathObtainer.class.getClassLoader().getResource(res);
        if (url != null){
            String osName = System.getProperty("os.name").toLowerCase();
            if (osName.contains("windows"))
                return Paths.get(url.getPath().replaceFirst("/", ""));
            else
                return Paths.get(url.getPath());
        } else {
            return null;
        }
    }

    public static String getProperPathString(String res){
        URL url = PathObtainer.class.getClassLoader().getResource(res);
        if (url != null){
            String osName = System.getProperty("os.name").toLowerCase();
                if (osName.contains("windows"))
                    return url.getPath().replaceFirst("/", "");
                else
                    return url.getPath();
        } else {
            return null;
        }
    }

}
