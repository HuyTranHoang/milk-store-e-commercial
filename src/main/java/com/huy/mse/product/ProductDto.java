package com.huy.mse.product;

import lombok.Getter;
import lombok.Setter;


import java.util.Date;

@Getter
@Setter
public class ProductDto {
    private long id;

    private String name;

    private String description;

    private double price;

    private int stock;

    private String batchNumber;

    private Date expiryDate;

    private String categoryName;

    private String brandName;
}
