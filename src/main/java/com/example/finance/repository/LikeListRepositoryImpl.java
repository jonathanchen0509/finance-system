package com.example.finance.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LikeListRepositoryImpl implements LikeListRepositoryCustom {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void callAddLikeSP(String userId, Integer productId, Integer quantity, String account) {
        jdbcTemplate.update(
                "CALL add_like(?,?,?,?)",
                userId, productId, quantity, account
        );
    }
}