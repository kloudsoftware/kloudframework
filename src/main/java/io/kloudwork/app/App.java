package io.kloudwork.app;

import io.kloudwork.config.Config;
import spark.Spark;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class App {

    private Config config;
    private EntityManager entityManager;

    public App() {

    }

    public void start() {
        config = initConfig();
        entityManager = initDatabase();
        register();
    }

    public abstract void register();

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

        String database = config.getProperties().getProperty("database.database");
        String host = config.getProperties().getProperty("database.host");

        String jdbcUrl = "jdbc:mysql://" + host + "/"  + database;

        props.put("hibernate.connection.url", jdbcUrl);
        props.put("hibernate.connection.username", config.getProperties().getProperty("database.user"));
        props.put("hibernate.connection.password", config.getProperties().getProperty("database.password"));
        final EntityManagerFactory factory = Persistence.createEntityManagerFactory("mysql", props);

        return factory.createEntityManager();
    }

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
