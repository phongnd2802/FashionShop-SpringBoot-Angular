package com.caonguyen.fashionshop.repositories;

import com.caonguyen.fashionshop.entities.discount.DiscountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountRepository extends JpaRepository<DiscountEntity, Long> {
    DiscountEntity findByCode(String discountCode);
}
