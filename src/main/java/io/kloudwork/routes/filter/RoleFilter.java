package io.kloudwork.routes.filter;

/*
 * RoleFilter.java - Checks if the user has the correct roles.
 *
 * Copyright 2017 kloud.software
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

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
