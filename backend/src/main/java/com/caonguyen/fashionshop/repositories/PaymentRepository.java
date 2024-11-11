package com.caonguyen.fashionshop.repositories;

import com.caonguyen.fashionshop.entities.payment.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity, Integer> {

}
