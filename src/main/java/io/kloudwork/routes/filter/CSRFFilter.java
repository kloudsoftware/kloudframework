package io.kloudwork.routes.filter;

/*
 * CSRFFilter.java - Checks if the csrf-token is set.
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

import spark.Filter;
import spark.Request;
import spark.Response;
import spark.Spark;

public class CSRFFilter implements Filter {
    @Override
    public void handle(Request request, Response response) throws Exception {
        String csrf = request.session().attribute("csrf-token");
        if (csrf == null) {
            throw Spark.halt(403, "No csrf token in session");
        }

        boolean reqContainsCsrf = request.body().contains(csrf);
        if (reqContainsCsrf) {
            return;
        }

        throw Spark.halt(403, "No csrf token in Post, something is fishy here!");
    }
}
