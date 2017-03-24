package io.kloudwork.security;

import io.kloudwork.models.User;

public class SecurityRoleProvider {

    public static boolean isAdmin(User user) {
        return user.getRole().equals(Roles.ADMIN);
    }
}
