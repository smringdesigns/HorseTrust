package com.vargas.horsemarket.domain.repository;

import com.vargas.horsemarket.domain.model.User;

import java.util.List;
import java.util.Optional;

/**
 * Puerto de dominio para persistencia de usuarios.
 * La implementación está en infrastructure.persistence.
 */
public interface UserRepository {

    User save(User user);

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    List<User> findAll();

    void deleteById(Long id);
}
