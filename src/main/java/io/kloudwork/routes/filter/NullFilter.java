package io.kloudwork.routes.filter;

import spark.Request;
import spark.Response;

public class NullFilter implements spark.Filter {
    @Override
    public void handle(Request request, Response response) throws Exception {
    }
}
