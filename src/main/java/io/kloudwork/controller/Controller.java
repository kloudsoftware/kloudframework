package io.kloudwork.controller;

/*
 * Controller.java - Basic controller implementation.
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
