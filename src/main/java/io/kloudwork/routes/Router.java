package io.kloudwork.routes;


import spark.Filter;
import spark.Spark;

import java.util.*;

public class Router {
    private Map<Route, List<Filter>> routes;

    public Router() {
        this.routes = new HashMap<>();
    }

    public void register(HTTPVerb verb, String path, spark.Route route) {
        if (verb == HTTPVerb.GET) {
            Spark.get(path, route);
        } else {
            Spark.post(path, route);
        }
        // TODO: 25.03.2017 Impl all other Verbs
    }

    public void register(HTTPVerb verb, String path, spark.Route route, Filter filter) {
        Route ourRoute = new Route(verb, path);
        if (verb == HTTPVerb.GET) {
            Spark.get(path, route);
        } else {
            Spark.post(path, route);
        }
        routes.put(ourRoute, Collections.singletonList(filter));

        // TODO: 25.03.2017 Impl all other Verbs
    }

    public void register(HTTPVerb verb, String path, spark.Route route, List<Filter> filters) {
        Route ourRoute = new Route(verb, path);
        if (verb == HTTPVerb.GET) {
            Spark.get(path, route);
        } else {
            Spark.post(path, route);
        }
        routes.put(ourRoute, filters);
        // TODO: 25.03.2017 Impl all other Verbs
    }

    public void registerWithAuth(String route) {

    }

    public void registerWithoutCSRF(String route) {

    }

    public void finish() {
        for (Route route : routes.keySet()) {
            List<Filter> filters = routes.get(route);
            Spark.before(route.getPath(), filters.toArray(new Filter[filters.size()]));
        }
    }
}
