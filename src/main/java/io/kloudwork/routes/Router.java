package io.kloudwork.routes;


import com.sun.istack.internal.Nullable;
import io.kloudwork.routes.filter.AuthFilter;
import io.kloudwork.routes.filter.CSRFFilter;
import io.kloudwork.routes.filter.NullFilter;
import spark.Filter;
import spark.Spark;

import java.util.*;

public class Router {
    private Map<Route, List<Filter>> routes;

    public Router() {
        this.routes = new HashMap<>();
    }

    public void register(HTTPVerb verb, String path, spark.Route route) {
        Route ourRoute = new Route(verb, path, route);
        routes.put(ourRoute, Collections.singletonList(new NullFilter()));
    }

    public void register(HTTPVerb verb, String path, spark.Route route, Filter filter) {
        Route ourRoute = new Route(verb, path, route);
        routes.put(ourRoute, Collections.singletonList(filter));
    }

    public void register(HTTPVerb verb, String path, spark.Route route, List<Filter> filters) {
        Route ourRoute = new Route(verb, path, route);
        routes.put(ourRoute, filters);
    }

    public void registerWithAuth(HTTPVerb verb, String path, spark.Route route, @Nullable List<Filter> filters) {
        if (filters == null) {
            filters = new ArrayList<>();
        }

        filters.add(new AuthFilter());

        register(verb, path, route, filters);
    }

    public void registerWithoutCSRF(String path, spark.Route route, @Nullable List<Filter> filters) {
        if (filters != null) {
            Spark.before(path, filters.toArray(new Filter[filters.size()]));
        }
        Spark.post(path, route);
    }

    private void addRoute(HTTPVerb verb, String path, spark.Route route) {
        switch (verb) {
            case GET:
                Spark.get(path, route);
                break;
            case POST:
                Spark.post(path, route);
                break;
            case PUT:
                Spark.put(path, route);
                break;
            case HEAD:
                Spark.head(path, route);
                break;
            case PATCH:
                Spark.patch(path, route);
                break;
            case TRACE:
                Spark.trace(path, route);
                break;
            case DELETE:
                Spark.delete(path, route);
                break;
            case CONNECT:
                Spark.connect(path, route);
                break;
            case OPTIONS:
                Spark.options(path, route);
                break;
        }
    }

    public void finish() {
        for (Map.Entry<Route, List<Filter>> routeFilterEntry : routes.entrySet()) {
            final Route route = routeFilterEntry.getKey();
            final List<Filter> filters = routeFilterEntry.getValue();
            addRoute(route.getVerb(), route.getPath(), route.getHandler());
            if (route.getVerb() == HTTPVerb.POST) {
                filters.add(new CSRFFilter());
            }
            Spark.before(route.getPath(), filters.toArray(new Filter[filters.size()]));
        }
    }
}
