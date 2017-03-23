package io.kloudwork.controller;

import spark.Request;
import spark.Response;

public class LoginController {
    private static LoginController ourInstance = new LoginController();

    public static LoginController getInstance() {
        return ourInstance;
    }

    private LoginController() {
    }

    public String login(Request request, Response response) {
        return "";
    }

    public String logout(Request request, Response response) {
        return "";
    }

    public String register(Request request, Response response) {
        return "";
    }

    public String postLogin(Request request, Response response) {
        request.body();

        return "";
    }

    public String postLogout(Request request, Response response) {
        return "";
    }

    public String postRegister(Request request, Response response) {
        return "";
    }
}
