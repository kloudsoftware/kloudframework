package io.kloudwork.routes;

public class Route {
    private final HTTPVerb verb;
    private final String path;
    private boolean csrfRequired = true;

    public Route(HTTPVerb verb, String path) {
        this.verb = verb;
        this.path = path;
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
}
