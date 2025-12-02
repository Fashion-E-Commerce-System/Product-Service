package com.ecommerce.backend.repository;

import com.ecommerce.backend.domain.GarmentGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GarmentGroupRepository extends JpaRepository<GarmentGroup, Integer> {
}
