package com.caonguyen.fashionshop.repositories;

import com.caonguyen.fashionshop.entities.product.SKUEntity;
import com.caonguyen.fashionshop.entities.product.SPUEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SKURepository extends JpaRepository<SKUEntity, Long> {
    SKUEntity findByCode(String code);
    List<SKUEntity> findBySpu(SPUEntity spu);
    List<SKUEntity> findBySpuCode(String spuCode);
}
