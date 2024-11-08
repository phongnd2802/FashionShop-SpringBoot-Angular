package com.caonguyen.fashionshop.repositories;

import com.caonguyen.fashionshop.entities.auth.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
    RoleEntity findByName(String name);
}
