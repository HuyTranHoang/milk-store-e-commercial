package com.huy.mse.brand;
import java.util.List;

public interface BrandService {
    List<BrandDto> getAllBrands();
    BrandDto getBrandById(long id);
    BrandDto createBrand(BrandDto brandDto);
    BrandDto updateBrand(BrandDto brandDto);
    void deleteBrand(long id);
}
