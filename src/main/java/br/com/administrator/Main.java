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

            // Cria um contexto sem um diretório base, pois os recursos estão no JAR.
            Context context = tomcat.addContext(contextPath, null);

            // Localiza o JAR que está em execução.
            final URL location = Main.class.getProtectionDomain().getCodeSource().getLocation();
            final String docBase = location.toURI().toString();

            // Cria um gerenciador de recursos para o contexto.
            WebResourceRoot resources = new StandardRoot(context);

            // Mapeia a raiz da aplicação web ('/') para a raiz DENTRO do JAR ('/').
            // Isso fará com que o Tomcat encontre o WEB-INF e suas páginas.
            resources.addPreResources(new JarResourceSet(resources, "/", docBase, "/"));
            context.setResources(resources);

            // Adiciona listeners essenciais para que o Tomcat processe o web.xml,
            // as anotações e outras configurações de dentro do JAR.
            context.addLifecycleListener(new Tomcat.DefaultWebXmlListener());
            context.addLifecycleListener(new Tomcat.FixContextListener());
        }

        tomcat.start();
        tomcat.getServer().await();
    }
}