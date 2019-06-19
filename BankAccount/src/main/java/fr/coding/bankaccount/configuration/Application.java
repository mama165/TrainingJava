package fr.coding.bankaccount.configuration;

import fr.coding.bankaccount.controller.AccountController;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;

public class Application {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);
    private static final String PATH_SPEC = "/*";
    private static final int PORT = 8080;
    private final Server server = new Server(PORT);

    void start() {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");

        server.setHandler(context);

        ServletHolder jerseyServlet = context.addServlet(
                org.glassfish.jersey.servlet.ServletContainer.class, PATH_SPEC);
        jerseyServlet.setInitOrder(0);

        // Tells the Jersey Servlet which REST service/class to load.
        jerseyServlet.setInitParameter(
                "jersey.config.server.provider.classnames",
                AccountController.class.getCanonicalName());

        try {
            logger.info(String.format("Server started at : %s", Instant.now()));
            server.start();
            server.join();
        } catch (Exception ex) {
            logger.error("An error occured when server started...");
        } finally {
            server.destroy();
            logger.info("Shutting down server at : " + Instant.now());
        }
    }
}
