package io.kloudwork.app;

import io.kloudwork.config.Config;
import io.kloudwork.controller.LoginController;
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
        Container.getInstance().setConfig(initConfig());
        Container.getInstance().setEntityManager(initDatabase());
        registerAuthRoutes();
        register();
    }

    protected void registerAuthRoutes() {
        LoginController loginController = LoginController.getInstance();
        Spark.get("/login", loginController::login);
        Spark.get("/logout", loginController::logout);
        Spark.get("/register", loginController::register);

        Spark.post("/login", loginController::postLogin);
        Spark.post("/logout", loginController::postLogout);
        Spark.post("/register", loginController::postRegister);
    }

    protected abstract void register();

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

        String jdbcUrl = "jdbc:mysql://" + host + "/"  + database;

        props.put("hibernate.connection.url", jdbcUrl);
        props.put("hibernate.connection.username", config.getProperties().getProperty("database.user"));
        props.put("hibernate.connection.password", config.getProperties().getProperty("database.password"));
        final EntityManagerFactory factory = Persistence.createEntityManagerFactory("mysql", props);

        return factory.createEntityManager();
    }

}
