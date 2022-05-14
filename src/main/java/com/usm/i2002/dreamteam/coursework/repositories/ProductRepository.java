package com.usm.i2002.dreamteam.coursework.repositories;

import com.usm.i2002.dreamteam.coursework.entities.Category;
import com.usm.i2002.dreamteam.coursework.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findByNameContainsIgnoreCase(final @NonNull String name, final Pageable pageable);

    @Query(value = "SELECT p.* FROM  t_products p WHERE p.category = ?1 ORDER BY RANDOM() LIMIT ?2",
            nativeQuery = true)
    List<Product> findProductsByTestResults(final String category, final Integer count);
}