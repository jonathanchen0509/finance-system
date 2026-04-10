package com.example.finance.service;

import com.example.finance.entity.LikeList;
import com.example.finance.entity.Product;
import com.example.finance.repository.LikeListRepository;
import com.example.finance.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LikeListService {

    private final LikeListRepository likeListRepository;
    private final ProductRepository productRepository;

    public LikeListService(LikeListRepository likeListRepository,
                           ProductRepository productRepository) {
        this.likeListRepository = likeListRepository;
        this.productRepository = productRepository;
    }

    //  新增喜好商品
    @Transactional
    public LikeList create(Long productNo, int quantity, String account) {

        // 取得商品（用來計算）
        Product product = productRepository.findById(productNo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product 不存在"));

        LikeList like = new LikeList();

        like.setPurchaseQuantity(quantity);
        like.setAccount(account);

        // 計算總金額與手續費
        double totalAmount = product.getPrice() * quantity;
        double totalFee = totalAmount * product.getFeeRate();

        like.setTotalAmount(totalAmount);
        like.setTotalFee(totalFee);

        return likeListRepository.save(like);
    }

    //  查詢全部
    public List<LikeList> getAll() {
        return likeListRepository.findAll();
    }

    //  刪除
    public void delete(Long sn) {
        likeListRepository.deleteById(sn);
    }

    //  更新
    @Transactional
    public LikeList update(Long sn, Long productId, int quantity, String account) {

        LikeList like = likeListRepository.findById(sn)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "資料不存在"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product 不存在"));

        like.setPurchaseQuantity(quantity);
        like.setAccount(account);

        double totalAmount = product.getPrice() * quantity;
        double totalFee = totalAmount * product.getFeeRate();

        like.setTotalAmount(totalAmount);
        like.setTotalFee(totalFee);

        return likeListRepository.save(like);
    }
}