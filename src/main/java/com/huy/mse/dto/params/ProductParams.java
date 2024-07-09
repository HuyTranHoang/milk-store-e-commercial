package com.huy.mse.dto.params;

import com.huy.mse.enums.ProductSortType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductParams {
    String name;
    String brandNameList;
    String categoryNameList;
    int pageNumber;
    int pageSize;
    ProductSortType sortBy;
}
