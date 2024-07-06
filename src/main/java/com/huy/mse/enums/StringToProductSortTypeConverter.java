package com.huy.mse.enums;
import org.springframework.core.convert.converter.Converter;

public class StringToProductSortTypeConverter implements Converter<String, ProductSortType> {
    @Override
    public ProductSortType convert(String source) {
        try {
            return ProductSortType.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            return ProductSortType.DEFAULT;
        }
    }
}