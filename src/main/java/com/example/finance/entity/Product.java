package com.example.finance.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long No;    // 產品流水號 (Primary Key)
    private String ProductName; //產品名稱
    private BigDecimal Price;   //產品價格
    private BigDecimal FeeRate; //手續費率(%)
}