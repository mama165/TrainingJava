package fr.coding.bankaccount.configuration;

import fr.coding.bankaccount.controller.AccountController;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;

public class Application {
    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);
    private static final int PORT = 8080;
    private static final String PATH_SPEC = "/*";

    void start() {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");

        Server server = new Server(PORT);
        server.setHandler(context);

        ServletHolder jerseyServlet = context.addServlet(
                org.glassfish.jersey.servlet.ServletContainer.class, PATH_SPEC);
        jerseyServlet.setInitOrder(0);

        // Tells the Jersey Servlet which REST service/class to load.
        jerseyServlet.setInitParameter(
                "jersey.config.server.provider.classnames",
                AccountController.class.getCanonicalName());

        try {
            server.start();
            server.join();
            LOGGER.info(String.format("Server started at : %s", Instant.now()));
        } catch (Exception ex) {
            LOGGER.warn("An error occured when server started...");
        } finally {
            server.destroy();
            LOGGER.info("Shutting down server");
        }
    }
}
