package fr.coding.bankaccount.configuration;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.glassfish.jersey.servlet.ServletContainer;

public class Client {
    private final static Logger logger = LoggerFactory.getLogger(Client.class);

    public static void main(String[] args) {
        Server server = new Server(8080);

        ServletContextHandler ctx =
                new ServletContextHandler(ServletContextHandler.NO_SESSIONS);

        ctx.setContextPath("/");
        server.setHandler(ctx);

        ServletHolder serHol = ctx.addServlet(ServletContainer.class, "/*");
        serHol.setInitOrder(1);
        String packageName = "fr.coding.bankaccount";
        serHol.setInitParameter("jersey.config.server.provider.packages",
                packageName);

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
