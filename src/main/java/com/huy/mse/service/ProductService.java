package com.huy.mse.service;

import com.huy.mse.dto.ProductDto;
import com.huy.mse.dto.params.ProductParams;

import java.util.Map;

public interface ProductService {

    Map<String, Object> getAllProducts(ProductParams productParams);

    ProductDto getProductById(long id);

    ProductDto createProduct(ProductDto productDto);

    ProductDto updateProduct(long id, ProductDto productDto);

    void deleteProduct(long id);
}
