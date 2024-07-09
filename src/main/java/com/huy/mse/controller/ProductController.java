package com.huy.mse.controller;

import com.huy.mse.dto.ProductDto;
import com.huy.mse.dto.params.ProductParams;
import com.huy.mse.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping({"", "/"})
    public ResponseEntity<Map<String, Object>> getProductsWithPagination(@ModelAttribute ProductParams productParams) {
        return ResponseEntity.ok(productService.getAllProductsWithParams(productParams));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable long id) {
        ProductDto productDto = productService.getById(id);
        return ResponseEntity.ok(productDto);
    }

    @PostMapping({"", "/"})
    public ResponseEntity<ProductDto> addProduct(@ModelAttribute ProductDto productDto) {
        ProductDto productReturn = productService.create(productDto);
        return ResponseEntity.ok(productReturn);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable long id, @ModelAttribute ProductDto productDto) {
        ProductDto productReturn = productService.update(id, productDto);
        return ResponseEntity.ok(productReturn);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable long id) {
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
