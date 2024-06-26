package com.huy.mse.category;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CategoryDto {
    private long id;

    private String name;

    private String description;

    private Date createdAt;
}
