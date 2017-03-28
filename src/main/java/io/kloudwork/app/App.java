package io.kloudwork.app;

import io.kloudwork.config.Config;
import io.kloudwork.controller.LoginController;
import io.kloudwork.routes.HTTPVerb;
import io.kloudwork.routes.Router;
import spark.Spark;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class App {



    public App() {

    }

    public void start() {
        System.setProperty("org.slf4j.simpleLogger.logFile", "System.out");
        Router router = new Router();
        Container.getInstance().setConfig(initConfig());
        Container.getInstance().setEntityManager(initDatabase());
        registerAuthRoutes(router);
        register(router);
        router.finish();
    }

    protected void registerAuthRoutes(Router router) {
        LoginController loginController = LoginController.getInstance();

        router.register(HTTPVerb.GET, "/login", loginController::login);
        router.register(HTTPVerb.GET, "/logout", loginController::logout);
        router.register(HTTPVerb.GET, "/register", loginController::register);

        Spark.post("/login", loginController::postLogin);
        Spark.post("/register", loginController::postRegister);
    }

    protected abstract void register(Router router);

    private Config initConfig() {
        Config config = null;
        try {
            config = new Config();
        } catch (IOException e) {
            e.printStackTrace();
            Spark.stop();
        }
        return config;
    }

    private EntityManager initDatabase() {
        Map<String, Object> props = new HashMap<>();

        final Config config = Container.getInstance().getConfig();
        String database = config.getProperties().getProperty("database.database");
        String host = config.getProperties().getProperty("database.host");
        String timeZone = config.getProperties().getProperty("database.timezone");

        String jdbcUrl = "jdbc:mysql://" + host + "/"  + database+ "?serverTimezone=" + timeZone;

        props.put("hibernate.connection.url", jdbcUrl);
        props.put("hibernate.connection.username", config.getProperties().getProperty("database.user"));
        props.put("hibernate.connection.password", config.getProperties().getProperty("database.password"));
        final EntityManagerFactory factory = Persistence.createEntityManagerFactory("mysql", props);

        return factory.createEntityManager();
    }

}
