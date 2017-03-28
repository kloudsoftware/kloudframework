package io.kloudwork.controller;

import io.kloudwork.app.Container;
import io.kloudwork.models.User;
import io.kloudwork.persistence.UserRepository;
import io.kloudwork.util.Renderer;
import org.apache.commons.fileupload.FileUploadException;
import org.mindrot.jbcrypt.BCrypt;
import spark.Request;
import spark.Response;
import spark.Session;
import spark.Spark;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Optional;
import java.util.Random;

public class LoginController {
    private static LoginController ourInstance = new LoginController();

    private final UserRepository userRepository;
    private final SecureRandom secureRandom = new SecureRandom();

    private LoginController() {
        userRepository = new UserRepository();
    }

    public static LoginController getInstance() {
        return ourInstance;
    }

    public String login(Request request, Response response) {
        return Renderer.render(new HashMap<>(), "login.ftl", request);
    }

    public String logout(Request request, Response response) {
        request.session().removeAttribute("username");
        request.session().removeAttribute("csrf-token");
        request.session().invalidate();
        return "Logged out";
    }

    public String register(Request request, Response response) {
        return "";
    }

    public String postLogin(Request request, Response response) throws IOException, FileUploadException {
        boolean matched = false;

        Optional<User> userOptional = userRepository.findByUserName(request.queryParams("username"));

        if (userOptional.isPresent()) {
            String userPassword = userOptional.get().getPassword();
            matched = BCrypt.checkpw(request.queryParams("password"), userPassword);
        }

        if (!matched) {
            throw Spark.halt(401);
        }

        User user = userOptional.get();

        final Session session = request.session();
        session.attribute("username", user.getUsername());
        session.attribute("csrf-token", generateCSRFToken(64));

        response.status(200);
        return "Logged in";
    }

    public String postRegister(Request request, Response response) throws IOException, FileUploadException {

        String salt = BCrypt.gensalt();
        String hash = BCrypt.hashpw(request.queryParams("password"), salt);

        User user = new User();
        user.setUsername(request.queryParams("username"));
        user.setPassword(hash);

        EntityManager entityManager = Container.getInstance().getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        return "registered";
    }

    private String generateCSRFToken(int length) {
        char[] validChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456879".toCharArray();
        Random random = new Random();
        char[] buffer = new char[length];
        for (int i = 0; i < length; ++i) {
            if ((i % 10) == 0) {
                random.setSeed(secureRandom.nextLong());
            }
            buffer[i] = validChars[random.nextInt(validChars.length)];
        }
        return new String(buffer);
    }
}
