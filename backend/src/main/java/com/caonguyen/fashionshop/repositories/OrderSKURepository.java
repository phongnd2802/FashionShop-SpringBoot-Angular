package com.caonguyen.fashionshop.repositories;

import com.caonguyen.fashionshop.entities.order.OrderSKUEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderSKURepository extends JpaRepository<OrderSKUEntity, Long> {

}
