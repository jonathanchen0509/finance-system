package com.example.finance.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class LikeList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sn;   // SN (Primary Key)
    private int purchaseQuantity;  // 購買數量
    private String account;        // 扣款帳號
    private double totalFee;       // 總手續費
    private double totalAmount;    // 總金額
}