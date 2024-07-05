package com.huy.mse.product;

import com.huy.mse.common.PageInfo;
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
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;


    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;

    }

    @Override
    public Map<String, Object> getAllProducts(ProductParams productParams) {

        Specification<Product> spec = ProductSpecification.searchByName(productParams.getName())
                .and(ProductSpecification.filterByBrand(productParams.getBrandNameList()))
                .and(ProductSpecification.filterByCategoryName(productParams.getCategoryNameList()));

        Sort sort = switch (productParams.getSortBy()) {
            case "priceAsc" -> Sort.by(Sort.Order.asc("price"));
            case "priceDesc" -> Sort.by(Sort.Order.desc("price"));
            default -> Sort.by(Sort.Order.asc("id"));
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
                .map(productMapper::toDto)
                .toList();

        HashMap<String, Object> response = new HashMap<>();
        response.put("pageInfo", pageInfo);
        response.put("data", productDtoList);

        return response;
    }

    @Override
    public ProductDto getProductById(long id) {
        return productRepository.findById(id)
                .map(productMapper::toDto)
                .orElseThrow(() -> new NoResultException("Product not found"));
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = productMapper.toEntity(productDto);

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

        return productMapper.toDto(productRepository.save(product));
    }

    @Override
    public ProductDto updateProduct(long id, ProductDto productDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NoResultException("Product not found"));

        productMapper.updateProductFromDto(productDto, product);

        if (productDto.getImage() != null) {
            try {
                deleteImage(product.getImageUrl());
                String fileName = addImage(productDto.getImage());
                product.setImageUrl(fileName);
            } catch (IOException e) {
                throw new NoResultException("Failed to upload image");
            }
        }

        return productMapper.toDto(productRepository.save(product));
    }

    @Override
    public void deleteProduct(long id) {
        Product product = productRepository.findById(id).orElse(null);

        if (product == null) {
            throw new NoResultException("Product not found");
        }

        productRepository.delete(product);
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
