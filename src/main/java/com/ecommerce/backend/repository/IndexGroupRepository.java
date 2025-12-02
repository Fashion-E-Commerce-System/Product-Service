package com.ecommerce.backend.repository;

import com.ecommerce.backend.domain.IndexGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndexGroupRepository extends JpaRepository<IndexGroup, Integer> {
}
