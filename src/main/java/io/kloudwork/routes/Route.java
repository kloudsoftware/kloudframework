package io.kloudwork.routes;

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
