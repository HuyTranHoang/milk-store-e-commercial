package com.huy.mse.service;

import com.huy.mse.dto.ProductDto;
import com.huy.mse.dto.params.ProductParams;
import com.huy.mse.entity.Product;

import java.util.Map;

public interface ProductService extends GenericService<Product, ProductDto> {
    Map<String, Object> getAllProductsWithParams(ProductParams productParams);
}
