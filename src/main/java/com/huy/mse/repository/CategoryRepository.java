package com.huy.mse.repository;

import com.huy.mse.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long>{

    Category findByName(String name);
    Category findByNameIsIgnoreCase(String categoryName);
}
