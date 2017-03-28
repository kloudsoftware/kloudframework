package io.kloudwork.util;

import spark.ModelAndView;
import spark.Request;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.HashMap;
import java.util.Map;

public class Renderer {
    private static FreeMarkerEngine engine = new FreeMarkerEngine();

    public static String render(Map<String, String> model, String viewName, Request request) {

        final String token = request.session().attribute("csrf-token");
        if  (token != null) {
            model.put("_csrftoken", token);
        }

        return engine.render(new ModelAndView(model, viewName));
    }

    public static String render(String viewName, Request request) {
        return render(new HashMap<>(), viewName, request);
    }
}
