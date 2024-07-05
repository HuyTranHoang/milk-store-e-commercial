package com.huy.mse.product;

import com.huy.mse.brand.Brand;
import com.huy.mse.brand.BrandRepository;
import com.huy.mse.category.Category;
import com.huy.mse.category.CategoryRepository;
import jakarta.persistence.NoResultException;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class ProductMapper {

    @Autowired
    protected CategoryRepository categoryRepository;
    @Autowired
    protected BrandRepository brandRepository;

    @Mapping(target = "brandId", source = "brand.id")
    @Mapping(target = "categoryId", source = "category.id")
    @Mapping(target = "brandName", source = "brand.name")
    @Mapping(target = "categoryName", source = "category.name")
    public abstract ProductDto toDto(Product product);

    @Mapping(target = "brand", source = "brandId", qualifiedByName = "mapBrand")
    @Mapping(target = "category", source = "categoryId", qualifiedByName = "mapCategory")
    public abstract Product toEntity(ProductDto productDto);

    @Mapping(target = "brand", source = "brandId", qualifiedByName = "mapBrand")
    @Mapping(target = "category", source = "categoryId", qualifiedByName = "mapCategory")
    public abstract void updateProductFromDto(ProductDto productDto, @MappingTarget Product product);

    @Named("mapCategory")
    public Category mapCategory(long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NoResultException("Category not found"));
    }

    @Named("mapBrand")
    public Brand mapBrand(long brandId) {
        return brandRepository.findById(brandId)
                .orElseThrow(() -> new NoResultException("Brand not found"));
    }
}
