package com.usm.i2002.dreamteam.coursework.repositories;

import com.usm.i2002.dreamteam.coursework.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByNameContainsIgnoreCase(@NonNull String name, Pageable pageable);
}