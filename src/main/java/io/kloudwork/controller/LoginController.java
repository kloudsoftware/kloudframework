package io.kloudwork.controller;

import io.kloudwork.app.Container;
import io.kloudwork.models.User;
import io.kloudwork.persistence.UserRepository;
import io.kloudwork.util.MultipartFormHandler;
import io.kloudwork.util.PostParamHolder;
import org.apache.commons.fileupload.FileUploadException;
import org.mindrot.jbcrypt.BCrypt;
import spark.Request;
import spark.Response;
import spark.Spark;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.util.Optional;

public class LoginController {
    private static LoginController ourInstance = new LoginController();

    private final UserRepository userRepository;

    private LoginController() {
        userRepository = new UserRepository();
    }

    public static LoginController getInstance() {
        return ourInstance;
    }

    public String login(Request request, Response response) {
        return "";
    }

    public String logout(Request request, Response response) {
        request.session().removeAttribute("username");
        request.session().invalidate();
        return "Logged out";
    }

    public String register(Request request, Response response) {
        return "";
    }

    public String postLogin(Request request, Response response) throws IOException, FileUploadException {
        PostParamHolder params = MultipartFormHandler.handle(request);
        boolean matched = false;

        Optional<User> userOptional = userRepository.findByUserName(params.getParameters().get("username"));

        if (userOptional.isPresent()) {
            String userPassword = userOptional.get().getPassword();
            matched = BCrypt.checkpw(params.getParameters().get("password"), userPassword);
        }

        if (!matched) {
            throw Spark.halt(401);
        }

        User user = userOptional.get();

        request.session().attribute("username", user.getUsername());

        response.status(200);
        return "Logged in";
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
        return "registered";
    }
}
