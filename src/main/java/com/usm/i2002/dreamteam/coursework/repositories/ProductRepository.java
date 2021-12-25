package com.usm.i2002.dreamteam.coursework.repositories;

import java.util.List;
import java.util.Optional;

import com.usm.i2002.dreamteam.coursework.entities.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByNameIgnoreCase(@NonNull String name);

    Page<Product> findByNameContainsIgnoreCase(@NonNull String name, Pageable pageable);

}