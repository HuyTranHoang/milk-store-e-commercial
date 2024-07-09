package com.huy.mse.entity.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class OrderDetailPK {
    @Column(name = "order_id")
    private long orderId;

    @Column(name = "product_id")
    private long productId;
}
