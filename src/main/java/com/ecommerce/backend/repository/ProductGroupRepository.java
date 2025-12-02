package com.ecommerce.backend.repository;

import com.ecommerce.backend.domain.ProductGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductGroupRepository extends JpaRepository<ProductGroup, String> {
}
