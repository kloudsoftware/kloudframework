package io.kloudwork.app;

import io.kloudwork.config.Config;

import javax.persistence.EntityManager;

public class Container {
    private static Container ourInstance = new Container();

    public static Container getInstance() {
        return ourInstance;
    }

    private Config config;
    private EntityManager entityManager;

    private Container() {
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    public Config getConfig() {
        return config;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
