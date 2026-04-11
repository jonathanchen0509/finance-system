package com.example.finance.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "like_list")
public class LikeList {
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sn;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "purchase_quantity")
    private Integer purchaseQuantity;

    @Column(name = "account")
    private String account;

    @Column(name = "total_fee")
    private BigDecimal totalFee;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

}