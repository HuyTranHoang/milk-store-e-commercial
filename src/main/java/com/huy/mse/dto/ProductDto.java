package com.huy.mse.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDto {
    long id;

    @NotEmpty(message = "Name is required")
    String name;

    String description;

    @NotEmpty(message = "Price is required")
    @Min(value = 0, message = "Price must be greater than 0")
    @Max(value = 1000000, message = "Price must be less than 1000000")
    double price;

    @NotEmpty(message = "Stock is required")
    @Min(value = 0, message = "Stock must be greater than 0")
    int stock;

    @NotEmpty(message = "Batch number is required")
    String batchNumber;

    @NotEmpty(message = "Expiry date is required")
    LocalDate expiryDate;

    @NotEmpty(message = "Image URL is required")
    String imageUrl;

    LocalDate createdAt;

    long categoryId;

    String categoryName;

    long brandId;

    String brandName;

    @JsonIgnore
    MultipartFile image;
}
