package com.usm.i2002.dreamteam.coursework.repositories;

import com.usm.i2002.dreamteam.coursework.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByNameIgnoreCase(final String name);

    Page<Product> findByNameContainsIgnoreCase(final @NonNull String name, final Pageable pageable);

    @Query(value = "SELECT p.* FROM  t_products p WHERE p.category = ?1 AND p.gender = ?2 AND p.ageCategory = ?3 ORDER BY RANDOM() LIMIT ?4", nativeQuery = true)
    List<Product> findProductsByTestResults(final String category, final String gender, final String ageCategory, final Integer count);
}