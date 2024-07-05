package com.huy.mse.product;

import com.huy.mse.category.Category_;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {

    public static Specification<Product> searchByName(String name) {
        return (root, query, cb) -> {
            if (name == null || name.isBlank())
                return cb.conjunction();

            return cb.like(cb.lower(root.get(Product_.NAME)), "%" + name.toLowerCase() + "%");
        };
    }

    public static Specification<Product> filterByBrand(String brandList) {
        return (root, query, cb) -> {

            if (brandList == null || brandList.isBlank())
                return cb.conjunction();

            String[] brands = brandList.split(",");
            return root.get(Product_.BRAND).in((Object) brands);
        };
    }

    public static Specification<Product> filterByCategoryName(String categoryList) {
        return (root, query, cb) -> {
            if (categoryList == null || categoryList.isBlank())
                return cb.conjunction();

            String[] categories = categoryList.split(",");
            return root.get(Product_.CATEGORY).get(Category_.NAME).in((Object) categories);
        };
    }
}
