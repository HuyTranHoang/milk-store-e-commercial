package com.huy.mse.repository;

import com.huy.mse.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends GenericRepository<Category> {

    Category findByName(String name);

    Category findByNameIsIgnoreCase(String categoryName);
}
