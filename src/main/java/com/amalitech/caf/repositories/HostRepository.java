package com.amalitech.caf.repositories;

import com.amalitech.caf.entities.HostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HostRepository extends JpaRepository<HostEntity, Long> {
}
