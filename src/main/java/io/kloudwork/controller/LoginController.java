package io.kloudwork.controller;

import io.kloudwork.app.Container;
import io.kloudwork.models.User;
import io.kloudwork.util.MultipartFormHandler;
import io.kloudwork.util.PostParamHolder;
import org.apache.commons.fileupload.FileUploadException;
import org.mindrot.jbcrypt.BCrypt;
import spark.Request;
import spark.Response;

import javax.persistence.EntityManager;
import java.io.IOException;

public class LoginController {
    private static LoginController ourInstance = new LoginController();

    private LoginController() {
    }

    public static LoginController getInstance() {
        return ourInstance;
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

    public String postLogin(Request request, Response response) throws IOException, FileUploadException {
        PostParamHolder params = MultipartFormHandler.handle(request);
        boolean matched = false;
        matched = BCrypt.checkpw(params.getParameters().get("password"), "$2a$10$O7K72Aoa1sg8LCrzz2Iu1ejJTpCcHaK11tSmTgPbAimxmxQxbOkn.");

        return String.valueOf(matched);
    }

    public String postLogout(Request request, Response response) {
        return "";
    }

    public String postRegister(Request request, Response response) throws IOException, FileUploadException {
        PostParamHolder params = MultipartFormHandler.handle(request);

        String salt = BCrypt.gensalt();
        String hash = BCrypt.hashpw(params.getParameters().get("password"), salt);

        User user = new User();
        user.setUsername(params.getParameters().get("username"));
        user.setPassword(hash);

        EntityManager entityManager = Container.getInstance().getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        return "";
    }
}
