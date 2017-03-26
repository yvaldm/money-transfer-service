package com.yvaldm.moneytransferservice;


import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

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

        ResourceConfig config = new ResourceConfig();
        config.packages("com.yvaldm.moneytransferservice.controller");
        ServletHolder servlet = new ServletHolder(new ServletContainer(config));

        ServletContextHandler context = new ServletContextHandler(server, "/*");
        context.addServlet(servlet, "/*");


        // Start Server
        server.start();
        server.join();
    }
}
