package io.kloudwork.routes.filter;

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
