package com.huy.mse.brand;

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
                .orElse(null);
    }

    @Override
    public BrandDto createBrand(BrandDto brandDto) {
        Brand brand = brandMapper.toEntity(brandDto);
        return brandMapper.toDto(brandRepository.save(brand));
    }

    @Override
    public BrandDto updateBrand(BrandDto brandDto) {
        Brand brand = brandRepository.findById(brandDto.getId()).orElse(null);

        if (brand == null) {
            return null;
        }

        brand.setName(brandDto.getName());
        brand.setDescription(brandDto.getDescription());

        return brandMapper.toDto(brandRepository.save(brand));
    }

    @Override
    public void deleteBrand(long id) {
        brandRepository.deleteById(id);
    }
}
