package io.kloudwork.util;

import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

public class Renderer {
    private static FreeMarkerEngine engine = new FreeMarkerEngine();

    public static String render(Object model, String viewName) {
        return engine.render(new ModelAndView(model, viewName));
    }
}
