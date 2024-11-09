package com.caonguyen.fashionshop.repositories;

import com.caonguyen.fashionshop.entities.product.SKUEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SKURepository extends JpaRepository<SKUEntity, Long> {
    SKUEntity findByCode(String code);
}
