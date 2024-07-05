package com.huy.mse.service.impl;

import com.huy.mse.dto.BrandDto;
import com.huy.mse.mapper.BrandMapper;
import com.huy.mse.repository.BrandRepository;
import com.huy.mse.entity.Brand;
import com.huy.mse.service.BrandService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;

    public BrandServiceImpl(BrandRepository brandRepository, BrandMapper brandMapper) {
        this.brandRepository = brandRepository;
        this.brandMapper = brandMapper;
    }

    @Override
    public List<BrandDto> getAllBrands() {
        return brandRepository.findAll()
                .stream()
                .map(brandMapper::toDto)
                .toList();
    }

    @Override
    public BrandDto getBrandById(long id) {
        return brandRepository.findById(id)
                .map(brandMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Brand not found"));
    }

    @Override
    public BrandDto createBrand(BrandDto brandDto) {
        if (brandRepository.findByName(brandDto.getName()) != null) {
            throw new RuntimeException("Brand already exists");
        }

        Brand brand = brandMapper.toEntity(brandDto);
        return brandMapper.toDto(brandRepository.save(brand));
    }

    @Override
    public BrandDto updateBrand(BrandDto brandDto) {
        Brand brand = brandRepository.findById(brandDto.getId())
                .orElseThrow(() -> new RuntimeException("Brand not found"));

        brand.setName(brandDto.getName());
        brand.setDescription(brandDto.getDescription());

        return brandMapper.toDto(brandRepository.save(brand));
    }

    @Override
    public void deleteBrand(long id) {
        Brand brand = brandRepository.findById(id).orElse(null);

        if (brand == null) {
            throw new RuntimeException("Brand not found");
        }

        brandRepository.delete(brand);
    }
}
