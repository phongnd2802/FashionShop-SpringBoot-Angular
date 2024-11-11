package com.caonguyen.fashionshop.repositories;

import com.caonguyen.fashionshop.entities.order.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, String> {

}
