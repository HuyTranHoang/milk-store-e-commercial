package com.huy.mse.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CategoryDto {
    private long id;

    @NotEmpty(message = "Name is required")
    private String name;

    private String description;

    private LocalDate createdAt;
}
