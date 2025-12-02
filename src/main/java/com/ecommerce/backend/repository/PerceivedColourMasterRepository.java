package com.ecommerce.backend.repository;

import com.ecommerce.backend.domain.PerceivedColourMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerceivedColourMasterRepository extends JpaRepository<PerceivedColourMaster, Integer> {
}
