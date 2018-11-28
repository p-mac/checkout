package org.test.itv.checkout.server;

import org.test.itv.checkout.config.ConfigManager;
import org.test.itv.checkout.config.RoutingConfig;
import org.test.itv.checkout.config.ServerConfig;
import org.test.itv.checkout.datasource.DataSourceProvider;
import org.test.itv.checkout.logging.LogManager;
import org.test.itv.checkout.logging.Logger;
import spark.Spark;

import java.io.File;
import java.io.InvalidObjectException;

/**
 * The Server class uses Sparkjava to implement a Serverless REST Api and load the Routes from the configuration
 */
public class Server {
    private static Logger logger = LogManager.getLogger(Server.class);
    private static final Server instance = new Server();
    private static ServerConfig config;
    private static RoutingConfig routesMap;
    public static Server getInstance() {
        return instance;
    }

    private Server() {
        init();
    }

    /**
     * the init method loads the server and routing config
     */
    public Server init() {
        System.out.println("\nCHECKOUT - HTTPD - Server Bootstrap");
        try {
            config = ConfigManager.getInsntance().getConfig("server", ServerConfig.class);
            routesMap = ConfigManager.getInsntance().getConfig("routing", RoutingConfig.class);
            DataSourceProvider.getInstance().init();
        } catch (InvalidObjectException e) {
            logger.error("ERROR while loading server config", e);
        }
        return this;
    }

    /**
     * The start method activates/loads the Routes endpoints in the Sparkjava server process
     */
    public Server start() {
        System.out.println("Server Initialisation");
        Spark.port(config.getPort());
        if (config.getBasePath() != null) {
            File staticPath = new File(config.getBasePath());
            Spark.staticFiles.externalLocation(staticPath.getAbsolutePath());
            Spark.staticFiles.expireTime(600L);
        }
        Spark.init();

        routesMap.getRoutes().values().forEach(route -> {

            switch (route.getMethod()) {
                case GET:
                    Spark.get(route.getUri(), new GetRouteingHandler<>(route));
                    break;
                case POST:
                    Spark.post(route.getUri(), new PostRoutingHandler<>(route));
                    break;
                case PUT:
                    Spark.put(route.getUri(), new PutRoutingHandler<>(route));
                    break;
                case DELETE:
                    Spark.delete(route.getUri(), new DeleteRoutingHandler<>(route));
                    break;
            }
        });

        Spark.get("/check", (request, response) -> {
            return config.getWelcome();
        });

        Spark.get("/stop", (request, response) -> {
            stop();
            return "CHECKOUT APP - Server Shutdown \nbye";
        });
        System.out.println("Server initialisation complete");
        return this;
    }

    /**
     * this method stops the Sparkjava server process
     */
    public void stop() {
        DataSourceProvider.getInstance().destroy();
        Spark.stop();
        System.out.println("\nCHECKOUT APP - Server Shutdown");
    }
}
