package com.huy.mse.mapper;

import com.huy.mse.dto.CategoryDto;
import com.huy.mse.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDto toDto(Category category);

    Category toEntity(CategoryDto categoryDto);
}
