package br.com.administrator;

import org.apache.catalina.Context;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.JarResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import org.apache.tomcat.util.scan.StandardJarScanner;

import java.io.File;
import java.net.URL;

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
            // Cria um contexto vazio. O docBase é nulo porque vamos definir os recursos manualmente.
            Context context = tomcat.addContext(contextPath, null);

            // Descobre a localização do JAR que está em execução
            final URL location = Main.class.getProtectionDomain().getCodeSource().getLocation();
            final String docBase = location.toURI().getPath();

            // Aponta o gerenciador de recursos para o próprio JAR
            WebResourceRoot resources = new StandardRoot(context);
            // O caminho webAppMount é a raiz da aplicação, e o internalPath é onde os recursos
            // estão dentro do JAR (normalmente em META-INF/resources)
            resources.addPreResources(new JarResourceSet(resources, "/", docBase, "/META-INF/resources"));
            context.setResources(resources);

            // Como estamos configurando manualmente, precisamos registrar o FacesServlet
            Tomcat.addServlet(context, "FacesServlet", "jakarta.faces.webapp.FacesServlet");
            context.addServletMappingDecoded("*.jsf", "FacesServlet");

            // Também é bom adicionar o listener do Weld (CDI) aqui
            context.addApplicationListener("org.jboss.weld.environment.servlet.Listener");
        }

        tomcat.start();
        tomcat.getServer().await();
    }
}