package br.com.administrator;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import java.io.File;

public class Main {
    public static void main(String[] args) throws Exception {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.getConnector();

        Context context = tomcat.addWebapp("", new File(".").getAbsolutePath());
        Tomcat.initWebappDefaults(context);

        tomcat.start();
        tomcat.getServer().await();
    }
}