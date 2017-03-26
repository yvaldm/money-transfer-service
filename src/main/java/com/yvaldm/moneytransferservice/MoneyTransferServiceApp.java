package com.yvaldm.moneytransferservice;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

/**
 * MoneyTransferService main application
 */
public class MoneyTransferServiceApp {

    public static final String SCAN_PACKAGE = "com.yvaldm.moneytransferservice.controller";

    public static void main(String[] args) throws Throwable {
        Server server = new Server(8080);
        ResourceConfig config = new ResourceConfig();
        config.packages(SCAN_PACKAGE);
        ServletHolder servlet = new ServletHolder(new ServletContainer(config));
        ServletContextHandler context = new ServletContextHandler(server, "/*");
        context.addServlet(servlet, "/*");
        server.start();
        server.join();
    }
}
