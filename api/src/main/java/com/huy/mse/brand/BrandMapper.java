package com.huy.mse.brand;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BrandMapper {

    BrandDto toDto(Brand brand);

    Brand toEntity(BrandDto brandDto);
}
