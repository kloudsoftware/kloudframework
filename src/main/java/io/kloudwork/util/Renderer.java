package io.kloudwork.util;

/*
 * Renderer.java - Renders a freemarker view.
 *
 * Copyright 2017 kloud.software
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
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
