package com.yvaldm.moneytransferservice;

import com.yvaldm.moneytransferservice.controller.HelloServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.util.resource.Resource;

import java.net.URI;
import java.net.URL;

/**
 *
 * MoneyTransferService main application
 *
 */
public class MoneyTransferServiceApp {

    public static void main(String[] args) throws Throwable {
        try {
            new MoneyTransferServiceApp().run();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public void run() throws Exception {
        Server server = new Server(8080);

        URL webRootLocation = this.getClass().getResource("/webroot/index.html");
        if (webRootLocation == null) {
            throw new IllegalStateException("Unable to determine webroot URL location");
        }

        URI webRootUri = URI.create(webRootLocation.toURI().toASCIIString().replaceFirst("/index.html$", "/"));
        System.err.printf("Web Root URI: %s%n", webRootUri);

        ServletContextHandler context = new ServletContextHandler();
        context.setContextPath("/");
        context.setBaseResource(Resource.newResource(webRootUri));
        context.setWelcomeFiles(new String[]{"index.html"});

        context.getMimeTypes().addMimeMapping("txt", "text/plain;charset=utf-8");

        //WebAppContext
        server.setHandler(context);

        // Add Servlet endpoints
        context.addServlet(HelloServlet.class, "/hello/");
        context.addServlet(DefaultServlet.class, "/");

        // Start Server
        server.start();
        server.join();
    }
}
