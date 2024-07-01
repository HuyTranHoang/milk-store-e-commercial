package com.huy.mse.product;

import java.util.List;

public interface ProductService {

    List<ProductDto> getAllProducts();

    ProductDto getProductById(long id);

    ProductDto createProduct(ProductDto productDto);

    ProductDto updateProduct(long id, ProductDto productDto);

    void deleteProduct(long id);
}
