package io.kloudwork.controller;

import io.kloudwork.util.Renderer;
import spark.Request;
import spark.Response;

import java.util.HashMap;

public class Controller {
    private static Controller ourInstance = new Controller();

    private Controller() {
    }

    public static Controller getInstance() {
        return ourInstance;
    }

    public String index(Request request, Response response) {
        final HashMap<String, String> model = new HashMap<>();
        return Renderer.render(model, "index.ftl", request);
    }

    public String register(Request request, Response response) {
        final HashMap<String, String> model = new HashMap<>();
        return Renderer.render(model, "register.ftl", request);
    }
}
