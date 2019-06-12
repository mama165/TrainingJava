package fr.coding.bankaccount.configuration;

import fr.coding.bankaccount.controller.AccountController;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.glassfish.jersey.servlet.ServletContainer;

public class Client {
    private final static Logger logger = LoggerFactory.getLogger(Client.class);

    public static void main(String[] args) {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");

        Server server = new Server(8080);
        server.setHandler(context);

        ServletHolder jerseyServlet = context.addServlet(
                org.glassfish.jersey.servlet.ServletContainer.class, "/*");
        jerseyServlet.setInitOrder(0);

        // Tells the Jersey Servlet which REST service/class to load.
        jerseyServlet.setInitParameter(
                "jersey.config.server.provider.classnames",
                AccountController.class.getCanonicalName());

        try {
            server.start();
            server.join();
        } catch (Exception ex) {
            logger.warn("An error occured when server started...");
        } finally {
            logger.info("Shutting down server");
            server.destroy();
        }
    }
}
