package com.huy.mse.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "product")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String name;

    String description;

    double price;

    int stock;

    String batchNumber;

    LocalDate expiryDate;

    @Column(name = "image_url")
    String imageUrl;

    @CreationTimestamp
    LocalDate createdAt;

    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    Brand brand;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    List<OrderDetail> orderDetails;
}
