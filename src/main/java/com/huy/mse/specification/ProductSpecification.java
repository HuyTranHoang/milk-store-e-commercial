package com.huy.mse.specification;

import com.huy.mse.entity.Category;
import com.huy.mse.entity.Category_;
import com.huy.mse.entity.Product;
import com.huy.mse.entity.Product_;
import com.huy.mse.repository.CategoryRepository;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductSpecification {

    public static Specification<Product> searchByName(String name) {
        return (root, query, cb) -> {
            if (!StringUtils.hasLength(name))
                return cb.conjunction();

            return cb.like(cb.lower(root.get(Product_.NAME)), "%" + name.toLowerCase() + "%");

        };
    }

    public static Specification<Product> filterByBrand(String brandList) {
        return (root, query, cb) -> {

            if (!StringUtils.hasLength(brandList))
                return cb.conjunction();

            List<Predicate> predicates = new ArrayList<>();
            String[] brands = brandList.split(",");
            Arrays.stream(brands).forEach(
                    brand -> predicates.add(cb.equal(root.get(Product_.BRAND), brand.trim()))
            );

            return cb.or(predicates.toArray(new Predicate[0]));

        };
    }

    public static Specification<Product> filterByCategoryName(String categoryList, CategoryRepository categoryRepository) {
        return (root, query, cb) -> {
            if (!StringUtils.hasLength(categoryList))
                return cb.conjunction();

            Join<Product, Category> productCategoryJoin = root.join("category");
            List<Predicate> predicates = new ArrayList<>();
            String[] categories = categoryList.split(",");
            Arrays.stream(categories).forEach(
                    categoryName -> {
                        Category category = categoryRepository.findByNameIsIgnoreCase(categoryName.trim());

                        if (category != null)
                            predicates.add(cb.equal(productCategoryJoin.get(Category_.ID), category.getId()));
                    }
            );

            return cb.or(predicates.toArray(new Predicate[0]));

        };
    }
}
