package com.huy.mse.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.huy.mse.brand.Brand;
import com.huy.mse.category.Category;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "product")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String description;

    private double price;

    private int stock;

    private String batchNumber;

    private Date expiryDate;

    @CreationTimestamp
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;
}
