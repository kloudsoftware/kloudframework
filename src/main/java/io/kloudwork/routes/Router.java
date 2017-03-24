package io.kloudwork.routes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Router {
    Map<Route, List<Filter>> routes;

    public Router() {
        this.routes = new HashMap<>();
    }

    public void register(HTTPVerb verb, String route) {
    }

    public void register(HTTPVerb verb, String, Filter filter){

    }

    public void register(HTTPVerb verb, String, List<Filter> filters) {

    }

    public void registerWithAuth(HTTPVerb verb, String route) {

    }

    public void registerWithoutCSRF(HTTPVerb verb, String route) {

    }


}
