package com.caonguyen.fashionshop.repositories;

import com.caonguyen.fashionshop.entities.auth.VerifyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerifyRepository extends JpaRepository<VerifyEntity, Long> {
    VerifyEntity findByEmail(String email);
    VerifyEntity findByVerifyKeyHash(String verifyKeyHash);
}
