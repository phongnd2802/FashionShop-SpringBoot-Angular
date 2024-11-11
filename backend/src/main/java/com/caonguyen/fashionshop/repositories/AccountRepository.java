package com.caonguyen.fashionshop.repositories;

import com.caonguyen.fashionshop.entities.auth.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    AccountEntity findByEmail(String email);
    AccountEntity findById(long id);
}
