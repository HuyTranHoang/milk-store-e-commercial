package com.huy.mse.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "brand")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String name;

    String description;

    @CreationTimestamp
    @Column(name = "created_at")
    LocalDate createdAt;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "brand")
    List<Product> products;
}
