package com.huy.mse.product;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface ProductService {

    Map<String, Object> getAllProducts(ProductParams productParams);

    ProductDto getProductById(long id);

    ProductDto createProduct(ProductDto productDto);

    ProductDto updateProduct(long id, ProductDto productDto);

    void deleteProduct(long id);
}
