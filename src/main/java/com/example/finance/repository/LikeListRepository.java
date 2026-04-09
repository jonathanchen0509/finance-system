package com.example.finance.repository;

import com.example.finance.entity.LikeList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeListRepository extends JpaRepository<LikeList, Long> {
}