package com.ecommerce.backend.repository;

import com.ecommerce.backend.domain.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionRepository extends JpaRepository<Section, Integer> {
}
