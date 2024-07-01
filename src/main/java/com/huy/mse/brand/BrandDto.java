package com.huy.mse.brand;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BrandDto {
    private long id;

    @NotEmpty(message = "Name is required")
    private String name;

    private String description;

    private LocalDate createdAt;
}
