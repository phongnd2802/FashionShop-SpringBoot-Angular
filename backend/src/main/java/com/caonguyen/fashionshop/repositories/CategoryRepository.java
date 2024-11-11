package com.caonguyen.fashionshop.repositories;

import com.caonguyen.fashionshop.entities.product.CategoryEntity;
import jdk.jfr.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
    CategoryEntity findByName(String name);
}
