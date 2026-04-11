package com.example.finance.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class LikeListResponse {

    private String userName;
    private String email;
    private String account;
    private BigDecimal totalBaseAmount;
    private BigDecimal totalFee;
    private BigDecimal totalAmount;
    private List<ProductItem> products;

    @Data
    public static class ProductItem {
        private String account;
        private String productName;
        private Integer quantity;
        private BigDecimal baseAmount;
        private BigDecimal totalFee;
        private BigDecimal totalAmount;
        private Integer sn;
        private Long productNo;
    }
}