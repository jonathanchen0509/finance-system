package com.example.finance.dto;
import lombok.Data;

@Data
public class LikeListRequest {

    private String userId;
    private Long productId;
    private Integer quantity;
    private String account;
}