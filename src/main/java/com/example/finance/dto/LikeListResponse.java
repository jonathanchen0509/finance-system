package com.example.finance.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class LikeListResponse {

    private String userName;
    private String email;
    private String account;
    private BigDecimal totalAmount;
    private BigDecimal totalFee;
    private List<ProductItem> products;

    @Data
    public static class ProductItem {
        private String productName;
        private Integer quantity;
        private BigDecimal totalAmount;
        private BigDecimal totalFee;
    }
}