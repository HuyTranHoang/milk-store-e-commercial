package com.huy.mse.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BrandDto {
    long id;

    @NotEmpty(message = "Name is required")
    String name;

    String description;

    LocalDate createdAt;
}
