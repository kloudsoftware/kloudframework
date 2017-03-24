package io.kloudwork.persistence;

import java.util.List;
import java.util.Optional;

public interface KloudRepository<T, V extends Number> {

    List<T> findAll();

    Optional<T> findById(V id);
}
