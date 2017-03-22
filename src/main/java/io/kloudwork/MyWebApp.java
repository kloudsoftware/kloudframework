package io.kloudwork;

import io.kloudwork.app.App;
import io.kloudwork.controller.Controller;
import spark.Spark;

public final class MyWebApp extends App {

    public static void main(String[] args) {
        new MyWebApp().start();
    }

    /**
     * Register your routes and middleware here.
     */
    @Override
    public void register() {
        Controller controller = Controller.getInstance();
        Spark.get("/", controller::index);
    }
}
