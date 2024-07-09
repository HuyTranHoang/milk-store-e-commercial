package com.huy.mse.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "order")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    double totalPrice;

    LocalDate orderDate;

    String status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    UserEntity user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    List<OrderDetail> orderDetails;

    @CreationTimestamp
    @Column(name = "created_at")
    LocalDate createdAt;

}
