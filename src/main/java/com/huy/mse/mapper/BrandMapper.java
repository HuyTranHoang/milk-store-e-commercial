package com.huy.mse.mapper;

import com.huy.mse.dto.BrandDto;
import com.huy.mse.entity.Brand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BrandMapper {

    BrandDto toDto(Brand brand);

    Brand toEntity(BrandDto brandDto);
}
