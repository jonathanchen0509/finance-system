package com.example.finance.repository;

import com.example.finance.entity.LikeList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface LikeListRepository extends JpaRepository<LikeList, Integer>, LikeListRepositoryCustom {
    @Query(value = """
    SELECT u.user_name, u.email, l.account,
           p.product_name, l.purchase_quantity,
           l.total_amount, l.total_fee
    FROM like_list l
    JOIN users u ON l.user_id = u.user_id
    JOIN product p ON l.product_id = p.id
    WHERE l.user_id = :userId
""", nativeQuery = true)
    List<Object[]> findLikeListByUserId(String userId);
}


