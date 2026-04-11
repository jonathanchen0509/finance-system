package com.example.finance.repository;

public interface LikeListRepositoryCustom {
    void callAddLikeSP(String userId, Long productId, Integer quantity, String account);
}