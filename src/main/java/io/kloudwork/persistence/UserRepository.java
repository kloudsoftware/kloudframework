package io.kloudwork.persistence;

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
