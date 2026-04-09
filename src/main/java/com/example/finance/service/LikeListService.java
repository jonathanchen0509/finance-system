package com.example.finance.service;

import com.example.finance.entity.*;
import com.example.finance.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class LikeListService {

    private final LikeListRepository likeListRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public LikeListService(LikeListRepository likeListRepository,
                           UserRepository userRepository,
                           ProductRepository productRepository) {
        this.likeListRepository = likeListRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public LikeList create(String userId, Long productId, int quantity) {

        // 查 User（錯誤訊息）
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User 不存在"));

        // 查 Product
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product 不存在"));

        // 建立 LikeList
        LikeList like = new LikeList();
        like.setUser(user);
        like.setProduct(product);
        like.setQuantity(quantity);

        // 計算金額
        double totalAmount = product.getPrice() * quantity;
        double totalFee = totalAmount * product.getFeeRate();

        like.setTotalAmount(totalAmount);
        like.setTotalFee(totalFee);

        // 存資料
        return likeListRepository.save(like);
    }
}