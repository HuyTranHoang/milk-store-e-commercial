package com.huy.mse.enums;

import com.huy.mse.entity.Product_;
import org.springframework.data.domain.Sort;

public enum ProductSortType {
    PRICE_ASC {
        @Override
        public Sort getSort() {
            return Sort.by(Sort.Order.asc(Product_.PRICE));
        }
    },
    PRICE_DESC {
        @Override
        public Sort getSort() {
            return Sort.by(Sort.Order.desc(Product_.PRICE));
        }
    },
    DEFAULT {
        @Override
        public Sort getSort() {
            return Sort.by(Sort.Order.asc(Product_.ID));
        }
    };

    public abstract Sort getSort();
}