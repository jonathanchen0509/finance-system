package com.example.finance.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    private String userId;  //使用者ID (Primary Key)
    private String userName;    //使用者名稱
    private String email;   //使用者電子郵件
    private String account; //扣款帳號
}