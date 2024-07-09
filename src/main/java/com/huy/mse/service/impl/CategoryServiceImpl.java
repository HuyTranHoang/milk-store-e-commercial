package com.huy.mse.service.impl;

import com.huy.mse.dto.CategoryDto;
import com.huy.mse.repository.CategoryRepository;
import com.huy.mse.entity.Category;
import com.huy.mse.repository.GenericRepository;
import com.huy.mse.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends GenericServiceImpl<Category, CategoryDto> implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    protected GenericRepository<Category> getRepository() {
        return categoryRepository;
    }

    /*
        Helper methods for mapping
     */

    @Override
    public CategoryDto toDto(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .createdAt(category.getCreatedAt())
                .build();
    }

    @Override
    public Category toEntity(CategoryDto categoryDto) {
        return Category.builder()
                .id(categoryDto.getId())
                .name(categoryDto.getName())
                .description(categoryDto.getDescription())
                .build();
    }

    @Override
    public void updateEntityFromDto(Category category, CategoryDto categoryDto) {
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
    }
}
