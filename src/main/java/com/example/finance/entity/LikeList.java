package com.example.finance.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class LikeList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 關聯 User
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // 關聯 Product
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;

    private double totalAmount;
    private double totalFee;
}