package com.caonguyen.fashionshop.repositories;

import com.caonguyen.fashionshop.entities.product.SPUEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SPURepository extends JpaRepository<SPUEntity, Long> {
    SPUEntity findByCode(String code);
    List<SPUEntity> findByNameContainingIgnoreCase(String name);
    List<SPUEntity> findByCurrentPriceBetween(int minPrice, int maxPrice);
    List<SPUEntity> findByNameContainingIgnoreCaseAndCurrentPriceBetween(String name, int minPrice, int maxPrice);
}
