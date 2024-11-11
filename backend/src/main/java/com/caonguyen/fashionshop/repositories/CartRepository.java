package com.caonguyen.fashionshop.repositories;

import com.caonguyen.fashionshop.entities.auth.AccountEntity;
import com.caonguyen.fashionshop.entities.cart.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Long> {
    CartEntity findByAccount(AccountEntity account);
}
