package io.kloudwork.routes;

public class Route {
    private final HTTPVerb verb;
    private final String path;

    public Route(HTTPVerb verb, String path) {
        this.verb = verb;
        this.path = path;
    }

    public HTTPVerb getVerb() {
        return verb;
    }

    public String getPath() {
        return path;
    }
}
