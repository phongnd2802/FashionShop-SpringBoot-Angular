package com.caonguyen.fashionshop.repositories;

import com.caonguyen.fashionshop.entities.auth.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {
}
