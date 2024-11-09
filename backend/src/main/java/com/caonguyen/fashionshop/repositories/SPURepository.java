package com.caonguyen.fashionshop.repositories;

import com.caonguyen.fashionshop.entities.product.SPUEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SPURepository extends JpaRepository<SPUEntity, Long> {
    SPUEntity findByCode(String code);
}
