package com.huy.mse.category;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CategoryDto {
    private long id;

    @NotEmpty(message = "Name is required")
    private String name;

    private String description;

    private Date createdAt;
}
