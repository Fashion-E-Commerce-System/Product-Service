package com.ecommerce.backend.repository;


import com.ecommerce.backend.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}