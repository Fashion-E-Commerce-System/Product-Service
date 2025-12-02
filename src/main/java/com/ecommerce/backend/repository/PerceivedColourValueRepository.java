package com.ecommerce.backend.repository;

import com.ecommerce.backend.domain.PerceivedColourValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerceivedColourValueRepository extends JpaRepository<PerceivedColourValue, Integer> {
}
