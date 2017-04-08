package io.kloudwork.app;

/*
 * App.java - Interface to use Kloudframework.
 *
 * Copyright 2017 kloud.software
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

import io.kloudwork.config.Config;
import io.kloudwork.controller.LoginController;
import io.kloudwork.routes.HTTPVerb;
import io.kloudwork.routes.Router;
import org.hibernate.cfg.Configuration;
import org.hibernate.jpa.AvailableSettings;
import spark.Spark;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class App {


    public App() {

    }

    public void start() {
        Configuration cfg = new Configuration();
        cfg.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLInnoDBDialect");
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

        router.registerWithoutCSRF("/login", loginController::postLogin);
        router.registerWithoutCSRF("/register", loginController::postRegister);
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

    protected abstract void getEntityClasses(List<Class<?>> entityClasses);

    private EntityManager initDatabase() {
        Map<String, Object> props = new HashMap<>();

        final Config config = Container.getInstance().getConfig();
        String database = config.getProperties().getProperty("database.database");
        String host = config.getProperties().getProperty("database.host");
        String timeZone = config.getProperties().getProperty("database.timezone");

        String jdbcUrl = "jdbc:mysql://" + host + "/" + database + "?serverTimezone=" + timeZone;

        List<Class<?>> entityClasses = new ArrayList<>();

        this.getEntityClasses(entityClasses);

        props.put("hibernate.connection.url", jdbcUrl);
        props.put(AvailableSettings.LOADED_CLASSES, entityClasses);
        props.put("hibernate.connection.username", config.getProperties().getProperty("database.user"));
        props.put("hibernate.connection.password", config.getProperties().getProperty("database.password"));
        final EntityManagerFactory factory = Persistence.createEntityManagerFactory("mysql", props);

        return factory.createEntityManager();
    }

}
