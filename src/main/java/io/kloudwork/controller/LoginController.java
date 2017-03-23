package io.kloudwork.controller;

import io.kloudwork.util.MultipartFormHandler;
import io.kloudwork.util.PostParamHolder;
import org.apache.commons.fileupload.FileUploadException;
import spark.Request;
import spark.Response;

import javax.servlet.ServletException;
import java.io.IOException;

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

    public String postLogin(Request request, Response response) throws IOException, ServletException, FileUploadException {
        PostParamHolder paramHolder = MultipartFormHandler.handle(request);
        return "";
    }

    public String postLogout(Request request, Response response) {
        return "";
    }

    public String postRegister(Request request, Response response) {
        return "";
    }
}
