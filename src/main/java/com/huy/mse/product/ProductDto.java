package com.huy.mse.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Getter
@Setter
public class ProductDto {
    private long id;

    @NotEmpty(message = "Name is required")
    private String name;

    private String description;

    @NotEmpty(message = "Price is required")
    @Min(value = 0, message = "Price must be greater than 0")
    @Max(value = 1000000, message = "Price must be less than 1000000")
    private double price;

    @NotEmpty(message = "Stock is required")
    @Min(value = 0, message = "Stock must be greater than 0")
    private int stock;

    @NotEmpty(message = "Batch number is required")
    private String batchNumber;

    @NotEmpty(message = "Expiry date is required")
    private LocalDate expiryDate;

    @NotEmpty(message = "Image URL is required")
    private String imageUrl;

    private LocalDate createdAt;

    @Transient
    private long categoryId;

    private String categoryName;

    @Transient
    private long brandId;

    private String brandName;

    @Transient
    @JsonIgnore
    private MultipartFile image;
}
