package io.kloudwork.controller;

import spark.Request;
import spark.Response;
import io.kloudwork.util.Renderer;

import java.util.HashMap;

public class Controller {
    private static Controller ourInstance = new Controller();

    public static Controller getInstance() {
        return ourInstance;
    }

    private Controller() {
    }

    public String index(Request request, Response response) {
        final HashMap<String, String> model = new HashMap<>();
        return Renderer.render(model, "index.ftl");
    }
}
