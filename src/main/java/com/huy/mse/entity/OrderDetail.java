package com.huy.mse.entity;

import com.huy.mse.entity.embedded.OrderDetailPK;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Table(name = "order_detail")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDetail {

    @EmbeddedId
    OrderDetailPK id;

    double price;

    int quantity;

    @CreationTimestamp
    @Column(name = "created_at")
    LocalDate createdAt;

    @ManyToOne
    @MapsId("order_id")
    Order order;

    @ManyToOne
    @MapsId("product_id")
    Product product;
}
