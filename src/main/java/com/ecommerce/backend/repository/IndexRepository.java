package com.ecommerce.backend.repository;

import com.ecommerce.backend.domain.Index;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndexRepository extends JpaRepository<Index, String> {
}
