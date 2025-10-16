package br.com.administrator;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import org.apache.tomcat.util.scan.StandardJarScanner;

import java.io.File;

public class Main {
    public static void main(String[] args) throws Exception {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.getConnector();

        String contextPath = "/administrator";

        File webappDir = new File("src/main/webapp/");
        if (webappDir.exists()) {
            System.out.println("Configurando para ambiente de desenvolvimento...");

            Context context = tomcat.addWebapp(contextPath, webappDir.getAbsolutePath());

            File additionWebInfClasses = new File("target/classes");
            StandardRoot resources = new StandardRoot(context);
            resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes", additionWebInfClasses.getAbsolutePath(), "/"));
            context.setResources(resources);

            StandardJarScanner jarScanner = new StandardJarScanner();
            jarScanner.setScanClassPath(true);
            context.setJarScanner(jarScanner);
        } else {
            System.out.println("Configurando para ambiente de produção (JAR)...");
            Context context = tomcat.addWebapp(contextPath, "/");
            context.addLifecycleListener(new Tomcat.FixContextListener());
        }

        tomcat.start();
        tomcat.getServer().await();
    }
}