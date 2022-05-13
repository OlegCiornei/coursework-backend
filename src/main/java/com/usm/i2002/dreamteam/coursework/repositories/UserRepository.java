package com.usm.i2002.dreamteam.coursework.repositories;

import com.usm.i2002.dreamteam.coursework.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(final String email);
}