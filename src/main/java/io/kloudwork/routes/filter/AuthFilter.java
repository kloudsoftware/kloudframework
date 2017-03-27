package io.kloudwork.routes.filter;

import spark.Filter;
import spark.Request;
import spark.Response;
import spark.Spark;

public class AuthFilter implements Filter {

    @Override
    public void handle(Request request, Response response) throws Exception {
        if (request.session().attribute("username") != null) {
            return;
        }

        throw Spark.halt(403, "Forbidden");
    }
}
