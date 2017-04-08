package io.kloudwork.persistence;

/*
 * UserRepository.java - Repository to manage users in the database.
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

import io.kloudwork.app.Container;
import io.kloudwork.models.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

public class UserRepository implements KloudRepository<User, Long> {

    private final EntityManager entityManager = Container.getInstance().getEntityManager();

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("SELECT e from User e", User.class).getResultList();
    }

    @Override
    public Optional<User> findById(Long id) {
        try {
            return Optional.of(entityManager.createQuery("SELECT e from User e where id = :id", User.class)
                    .setParameter("id", id).getSingleResult());

        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    public Optional<User> findByUserName(String username) {

        try {
            return Optional.ofNullable(entityManager.createQuery("SELECT e from User e where username = :username", User.class)
                    .setParameter("username", username).getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
