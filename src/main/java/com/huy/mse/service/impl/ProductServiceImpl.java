package com.huy.mse.service.impl;

import com.huy.mse.common.PageInfo;
import com.huy.mse.dto.ProductDto;
import com.huy.mse.dto.params.ProductParams;
import com.huy.mse.entity.Brand;
import com.huy.mse.entity.Category;
import com.huy.mse.entity.Product;
import com.huy.mse.entity.Product_;
import com.huy.mse.enums.ProductSortType;
import com.huy.mse.repository.BrandRepository;
import com.huy.mse.repository.CategoryRepository;
import com.huy.mse.repository.GenericRepository;
import com.huy.mse.repository.ProductRepository;
import com.huy.mse.service.ProductService;
import com.huy.mse.specification.ProductSpecification;
import jakarta.persistence.NoResultException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@Service
public class ProductServiceImpl extends GenericServiceImpl<Product, ProductDto> implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, BrandRepository brandRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.brandRepository = brandRepository;
    }


    @Override
    protected GenericRepository<Product> getRepository() {
        return productRepository;
    }

    @Override
    public Map<String, Object> getAllProductsWithParams(ProductParams productParams) {

        Specification<Product> spec = ProductSpecification.searchByName(productParams.getName())
                .and(ProductSpecification.filterByBrand(productParams.getBrandNameList()))
                .and(ProductSpecification.filterByCategoryName(productParams.getCategoryNameList(), categoryRepository));

        ProductSortType productSortType = productParams.getSortBy();

        if (productSortType == null) {
            productSortType = ProductSortType.DEFAULT;
        }

        Sort sort = switch (productSortType) {
            case PRICE_ASC -> Sort.by(Sort.Order.asc(Product_.PRICE));
            case PRICE_DESC -> Sort.by(Sort.Order.desc(Product_.PRICE));
            default -> Sort.by(Sort.Order.asc(Product_.ID));
        };

        Pageable pageable = PageRequest.of(
                productParams.getPageNumber(),
                productParams.getPageSize(),
                sort
        );

        Page<Product> productPage = productRepository.findAll(spec, pageable);

        PageInfo pageInfo = new PageInfo(
                productPage.getNumber(),
                productPage.getTotalElements(),
                productPage.getTotalPages(),
                productPage.getSize()
        );

        List<ProductDto> productDtoList = productPage.stream()
                .map(this::toDto)
                .toList();

        HashMap<String, Object> response = new HashMap<>();
        response.put("pageInfo", pageInfo);
        response.put("data", productDtoList);

        return response;
    }

    @Override
    public ProductDto create(ProductDto productDto) {
        Product product = this.toEntity(productDto);

        if (productDto.getImage() != null) {
            try {
                String fileName = addImage(productDto.getImage());
                product.setImageUrl(fileName);
            } catch (IOException e) {
                throw new NoResultException("Failed to upload image");
            }
        } else {
            product.setImageUrl("default.png");
        }

        return this.toDto(productRepository.save(product));
    }

    @Override
    public ProductDto update(long id, ProductDto productDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NoResultException("Product not found"));

        this.updateEntityFromDto(product, productDto);

        if (productDto.getImage() != null) {
            try {
                deleteImage(product.getImageUrl());
                String fileName = addImage(productDto.getImage());
                product.setImageUrl(fileName);
            } catch (IOException e) {
                throw new NoResultException("Failed to upload image");
            }
        }

        return this.toDto(productRepository.save(product));
    }

    /*
        Helper methods for mapping
     */

    @Override
    public ProductDto toDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stock(product.getStock())
                .batchNumber(product.getBatchNumber())
                .expiryDate(product.getExpiryDate())
                .imageUrl(product.getImageUrl())
                .createdAt(product.getCreatedAt())
                .brandId(product.getBrand().getId())
                .brandName(product.getBrand().getName())
                .categoryId(product.getCategory().getId())
                .categoryName(product.getCategory().getName())
                .build();
    }

    @Override
    public Product toEntity(ProductDto productDto) {
        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new NoResultException("Category not found"));

        Brand brand = brandRepository.findById(productDto.getBrandId())
                .orElseThrow(() -> new NoResultException("Brand not found"));

        return Product.builder()
                .name(productDto.getName())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .stock(productDto.getStock())
                .batchNumber(productDto.getBatchNumber())
                .expiryDate(productDto.getExpiryDate())
                .imageUrl(productDto.getImageUrl())
                .createdAt(productDto.getCreatedAt())
                .category(category)
                .brand(brand)
                .build();
    }

    @Override
    public void updateEntityFromDto(Product product, ProductDto productDto) {
        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new NoResultException("Category not found"));

        Brand brand = brandRepository.findById(productDto.getBrandId())
                .orElseThrow(() -> new NoResultException("Brand not found"));

        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setStock(productDto.getStock());
        product.setBatchNumber(productDto.getBatchNumber());
        product.setExpiryDate(productDto.getExpiryDate());
        product.setImageUrl(productDto.getImageUrl());
        product.setCategory(category);
        product.setBrand(brand);
    }


    /*
        Helper methods for image upload and delete
     */

    private String addImage(MultipartFile image) throws IOException {
        String imageFileExtension = image.getContentType();

        List<String> allowedExtensions = Arrays.asList(MimeTypeUtils.IMAGE_JPEG_VALUE, MimeTypeUtils.IMAGE_PNG_VALUE);
        if (!allowedExtensions.contains(imageFileExtension)) {
            throw new NoResultException("Only PNG and JPEG images are allowed");
        }

        Path fileFolder = Path.of("upload/images/products");

        if (!Files.exists(fileFolder)) {
            Files.createDirectories(fileFolder);
        }

        String fileName = UUID.randomUUID() + "_" + image.getOriginalFilename();
        Path filePath = fileFolder.resolve(fileName);
        Files.copy(image.getInputStream(), filePath);

        return fileName;
    }

    private void deleteImage(String fileName) throws IOException {
        if (fileName.equals("default.png")) {
            return;
        }

        Path path = Path.of("upload/images/profile/" + fileName);
        Files.deleteIfExists(path);
    }
}
