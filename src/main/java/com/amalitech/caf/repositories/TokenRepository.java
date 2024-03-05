package com.amalitech.caf.repositories;

import com.amalitech.caf.entities.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TokenRepository extends JpaRepository<TokenEntity, Long> {
    TokenEntity findByToken(String token);

    //    @Query("SELECT t FROM TokenEntity t WHERE t.email= ?")
    TokenEntity findByEmail(String email);

    TokenEntity findByEmailAndToken(String email, String token);


    //    @Query("DELETE FROM TokenEntity t WHERE t.email= ?")
    void deleteByEmail(String email);

    void deleteByToken(String token);

    void deleteByEmailAndToken(String email, String token);
}
