package com.caonguyen.fashionshop.repositories;

import com.caonguyen.fashionshop.entities.discount.DiscountEntity;
import com.caonguyen.fashionshop.entities.product.SPUEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface DiscountRepository extends JpaRepository<DiscountEntity, Long> {
    DiscountEntity findByCode(String code);

    @Query("SELECT d FROM DiscountEntity d JOIN d.spuList s WHERE s = :spu AND d.active = true")
    Page<DiscountEntity> findActiveDiscountBySpu(@Param("spu") SPUEntity spu, Pageable pageable);
}
