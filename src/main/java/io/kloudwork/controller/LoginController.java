package io.kloudwork.controller;

import io.kloudwork.app.Container;
import io.kloudwork.models.User;
import io.kloudwork.util.MultipartFormHandler;
import io.kloudwork.util.PostParamHolder;
import org.apache.commons.fileupload.FileUploadException;
import spark.Request;
import spark.Response;

import javax.persistence.EntityManager;
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

    public String postLogin(Request request, Response response) {
        return "";
    }

    public String postLogout(Request request, Response response) {
        return "";
    }

    public String postRegister(Request request, Response response) throws IOException, FileUploadException {
        PostParamHolder params = MultipartFormHandler.handle(request);

        User user = new User();
        user.setUsername(params.getParameters().get("username"));
        user.setPassword(params.getParameters().get("password"));

        EntityManager entityManager = Container.getInstance().getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        return "";
    }
}
