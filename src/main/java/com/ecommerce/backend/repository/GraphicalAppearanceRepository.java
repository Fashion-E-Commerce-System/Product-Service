package com.ecommerce.backend.repository;

import com.ecommerce.backend.domain.GraphicalAppearance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GraphicalAppearanceRepository extends JpaRepository<GraphicalAppearance, Integer> {
}
