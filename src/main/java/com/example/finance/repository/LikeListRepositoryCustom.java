package com.example.finance.repository;

public interface LikeListRepositoryCustom {
    void callAddLikeSP(String userId, Integer productId, Integer quantity, String account);
}