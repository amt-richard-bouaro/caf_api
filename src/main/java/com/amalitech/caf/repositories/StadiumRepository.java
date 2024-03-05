package com.amalitech.caf.repositories;

import com.amalitech.caf.entities.StadiumEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Map;

public interface StadiumRepository extends JpaRepository<StadiumEntity, Long> {
    List<StadiumEntity> findStadiumByCityOrName(String city, String name);
}
