package com.huy.mse.service.impl;

import com.huy.mse.dto.BrandDto;
import com.huy.mse.entity.Brand;
import com.huy.mse.repository.BrandRepository;
import com.huy.mse.repository.GenericRepository;
import com.huy.mse.service.BrandService;
import org.springframework.stereotype.Service;

@Service
public class BrandServiceImpl extends GenericServiceImpl<Brand, BrandDto> implements BrandService {
    private final BrandRepository brandRepository;

    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    protected GenericRepository<Brand> getRepository() {
        return brandRepository;
    }

    /*
        Helper methods for mapping
     */


    @Override
    public BrandDto toDto(Brand brand) {
        return BrandDto.builder()
                .id(brand.getId())
                .name(brand.getName())
                .description(brand.getDescription())
                .createdAt(brand.getCreatedAt())
                .build();
    }

    @Override
    public Brand toEntity(BrandDto brandDto) {
        return Brand.builder()
                .id(brandDto.getId())
                .name(brandDto.getName())
                .description(brandDto.getDescription())
                .build();
    }

    @Override
    public void updateEntityFromDto(Brand brand, BrandDto brandDto) {
        brand.setName(brandDto.getName());
        brand.setDescription(brandDto.getDescription());
    }
}
