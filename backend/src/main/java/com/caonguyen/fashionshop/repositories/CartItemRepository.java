package com.caonguyen.fashionshop.repositories;

import com.caonguyen.fashionshop.entities.cart.CartEntity;
import com.caonguyen.fashionshop.entities.cart.CartItemEntity;
import com.caonguyen.fashionshop.entities.product.SKUEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItemEntity, Long> {
    CartItemEntity findByCartAndSku(CartEntity cartEntity, SKUEntity skuEntity);
    CartItemEntity findByCartIdAndSkuCode(Long id, String code);
    List<CartItemEntity> findByCart(CartEntity cartEntity);
}
