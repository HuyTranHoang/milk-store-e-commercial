package com.huy.mse.brand;

import jakarta.persistence.NoResultException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
public class BrandController {
    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping({"", "/"})
    public ResponseEntity<List<BrandDto>> getBrands() {
        return ResponseEntity.ok(brandService.getAllBrands());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandDto> getBrandById(@PathVariable long id) {
        BrandDto brandDto = brandService.getBrandById(id);

        if (brandDto == null) {
            throw new NoResultException("Brand not found");
        }

        return ResponseEntity.ok(brandDto);
    }

    @PostMapping({"", "/"})
    public ResponseEntity<BrandDto> addBrand(@RequestBody @Valid BrandDto brandDto) {
        BrandDto brandReturn = brandService.createBrand(brandDto);
        return ResponseEntity.ok(brandReturn);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BrandDto> updateBrand(@PathVariable long id, @RequestBody BrandDto brandDto) {
        brandDto.setId(id);
        BrandDto brandReturn = brandService.updateBrand(brandDto);

        if (brandReturn == null) {
            throw new NoResultException("Brand not found");
        }

        return ResponseEntity.ok(brandReturn);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable long id) {
        brandService.deleteBrand(id);
        return ResponseEntity.noContent().build();
    }
}
