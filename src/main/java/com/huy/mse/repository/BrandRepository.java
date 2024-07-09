package com.huy.mse.repository;

import com.huy.mse.entity.Brand;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends GenericRepository<Brand> {
    Brand findByName(String name);
}