package com.huy.mse.common;

import lombok.Data;

@Data
public class PageInfo {
    private int number;
    private long totalElements;
    private int totalPages;
    private int size;

    public PageInfo(int number, long totalElements, int totalPages, int size) {
        this.number = Math.max(number, 0);
        this.totalElements = totalElements;
        this.totalPages = totalPages;

        if (size <= 0 || size > 50) {
            this.size = 10;
        } else {
            this.size = size;
        }
    }
}
