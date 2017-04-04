package io.kloudwork.routes.filter;

import io.kloudwork.models.User;
import io.kloudwork.persistence.UserRepository;
import spark.Filter;
import spark.Request;
import spark.Response;
import spark.Spark;

import java.util.List;

public class RoleFilter implements Filter {

    private List<String> allowedRoles;

    public RoleFilter(List<String> rolesList) {
        allowedRoles = rolesList;
    }

    @Override
    public void handle(Request request, Response response) throws Exception {
        String username = request.session().attribute("username");
        if (username == null) {
            throw Spark.halt(401, "User not authenticated");
        }

        UserRepository userRepository = new UserRepository();
        User user = userRepository.findByUserName(username).orElseThrow(() -> Spark.halt(401, "User not found"));

        if (!allowedRoles.contains(user.getRole())) {
            throw Spark.halt(401, "User role not allowed");
        }
    }
}
