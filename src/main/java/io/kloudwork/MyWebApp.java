package io.kloudwork;

import io.kloudwork.app.App;
import io.kloudwork.controller.Controller;
import io.kloudwork.routes.HTTPVerb;
import io.kloudwork.routes.Router;
import org.hibernate.cfg.Configuration;
import spark.Filter;

import java.util.Arrays;
import java.util.List;

public final class MyWebApp extends App {

    public static void main(String[] args) {
        Configuration cfg = new Configuration();
        cfg.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLInnoDBDialect");
        new MyWebApp().start();
    }

    /**
     * Register your routes and middleware here.
     *
     * @param router
     */
    @Override
    protected void register(Router router) {
        Controller controller = Controller.getInstance();
        router.register(HTTPVerb.GET, "/", controller::index);
        List<Filter> myFilters = Arrays.asList((request, response) -> System.out.println("One filter"), (request, response) -> System.out.println("2nd Filter"));
        router.register(HTTPVerb.GET, "/2filters", controller::index, myFilters);
        router.register(HTTPVerb.GET, "/filter", controller::index, (request, response) -> System.out.println("Filtering works"));
        router.register(HTTPVerb.GET, "/csrf", controller::register);
    }


}
