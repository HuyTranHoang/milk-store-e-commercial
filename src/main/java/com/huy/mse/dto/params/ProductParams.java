package com.huy.mse.dto.params;

import com.huy.mse.enums.ProductSortType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductParams {
    private String name;
    private String brandNameList;
    private String categoryNameList;
    private int pageNumber = 0;
    private int pageSize = 10;
    private ProductSortType sortBy;
}
