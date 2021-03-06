package io.kloudwork.routes;

/*
 * Route.java - Implementation of a route.
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

public class Route {
    private final HTTPVerb verb;
    private final String path;
    private boolean csrfRequired = true;
    private final spark.Route handler;

    public Route(HTTPVerb verb, String path, spark.Route handler) {
        this.verb = verb;
        this.path = path;
        this.handler = handler;
    }

    public boolean isCsrfRequired() {
        return csrfRequired;
    }

    public void setCsrfRequired(boolean csrfRequired) {
        this.csrfRequired = csrfRequired;
    }

    public HTTPVerb getVerb() {
        return verb;
    }

    public String getPath() {
        return path;
    }

    public spark.Route getHandler() {
        return handler;
    }
}
