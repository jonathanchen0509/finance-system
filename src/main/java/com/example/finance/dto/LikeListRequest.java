package com.example.finance.dto;
import lombok.Data;

@Data
public class LikeListRequest {

    private String userId;
    private Long productNo;
    private Integer quantity;
    private String account;
}