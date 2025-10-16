package br.com.administrator;

import org.apache.catalina.startup.Tomcat;

import java.io.File;

public class Main {
    public static void main(String[] args) throws Exception {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.getConnector();

        tomcat.addWebapp("", new File(".").getAbsolutePath());

        tomcat.start();
        tomcat.getServer().await();
    }
}