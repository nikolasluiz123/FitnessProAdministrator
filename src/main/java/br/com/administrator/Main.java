package br.com.administrator;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.scan.StandardJarScanner;

import java.io.File;
import java.nio.file.Files;

public class Main {
    public static void main(String[] args) throws Exception {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.getConnector();

        File baseDir = Files.createTempDirectory("tomcat-base").toFile();
        baseDir.deleteOnExit();
        Context context = tomcat.addContext("", baseDir.getAbsolutePath());

        context.addLifecycleListener(new Tomcat.DefaultWebXmlListener());

        StandardJarScanner jarScanner = new StandardJarScanner();
        jarScanner.setScanClassPath(true);
        context.setJarScanner(jarScanner);

        tomcat.start();
        tomcat.getServer().await();
    }
}