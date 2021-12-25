package com.usm.i2002.dreamteam.coursework.repositories;

import com.usm.i2002.dreamteam.coursework.entities.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}