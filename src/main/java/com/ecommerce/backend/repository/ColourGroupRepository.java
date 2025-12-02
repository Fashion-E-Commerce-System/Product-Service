package com.ecommerce.backend.repository;

import com.ecommerce.backend.domain.ColourGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColourGroupRepository extends JpaRepository<ColourGroup, Integer> {
}
