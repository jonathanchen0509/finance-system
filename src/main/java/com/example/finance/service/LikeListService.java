package com.example.finance.service;

import com.example.finance.dto.LikeListRequest;
import com.example.finance.dto.LikeListResponse;
import com.example.finance.entity.LikeList;
import com.example.finance.entity.Product;
import com.example.finance.repository.LikeListRepository;
import com.example.finance.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor

public class LikeListService {

    private final LikeListRepository likeListRepository;
    private final ProductRepository productRepository;
    @Transactional
    public void addLike(LikeListRequest req) {
        if (req.getQuantity() <= 0) {
            throw new RuntimeException("購買數量需大於0");
        }
        likeListRepository.callAddLikeSP(
                req.getUserId(),
                req.getProductNo(),
                req.getQuantity(),
                req.getAccount()
        );
    }
    public LikeListResponse getLikeList(String userId) {

        List<Object[]> rows = likeListRepository.findLikeListByUserId(userId);

        LikeListResponse response = new LikeListResponse();

        BigDecimal totalAmount = BigDecimal.ZERO;
        BigDecimal totalFee = BigDecimal.ZERO;
        BigDecimal totalBaseAmount = BigDecimal.ZERO;
        List<LikeListResponse.ProductItem> items = new ArrayList<>();

        for (Object[] row : rows) {

            // 使用者資訊
            if (response.getUserName() == null) {
                response.setUserName((String) row[0]);
                response.setEmail((String) row[1]);
                response.setAccount((String) row[2]);
            }

            // 商品資料
            LikeListResponse.ProductItem item = new LikeListResponse.ProductItem();
            item.setProductName((String) row[3]);
            item.setQuantity((Integer) row[4]);
            item.setBaseAmount((BigDecimal) row[5]);
            item.setTotalFee((BigDecimal) row[6]);
            item.setTotalAmount((BigDecimal) row[7]);
            item.setSn((Integer) row[8]);
            item.setProductNo(((Integer) row[9]).longValue());


            totalBaseAmount = totalBaseAmount.add(item.getBaseAmount());
            totalFee = totalFee.add(item.getTotalFee());
            totalAmount = totalAmount.add(item.getTotalAmount());

            items.add(item);
        }

        response.setTotalBaseAmount(totalBaseAmount);
        response.setTotalFee(totalFee);
        response.setTotalAmount(totalAmount);
        response.setProducts(items);

        return response;
    }

    @Transactional
    public void deleteLike(Integer sn) {
        likeListRepository.deleteById(sn);
    }

    @Transactional
    public void updateLike(Integer sn, LikeListRequest req) {

        if (req.getQuantity() <= 0) {
            throw new RuntimeException("購買數量需大於0");
        }
        LikeList entity = likeListRepository.findById(sn)
                .orElseThrow(() -> new RuntimeException("找不到資料"));
        Product product = productRepository.findById(req.getProductNo())
                .orElseThrow(() -> new RuntimeException("商品不存在"));

        BigDecimal price = product.getPrice();
        BigDecimal feeRate = product.getFeeRate();

        //  計算
        BigDecimal baseAmount = price.multiply(BigDecimal.valueOf(req.getQuantity()));
        BigDecimal totalFee = baseAmount.multiply(feeRate);
        BigDecimal totalAmount = baseAmount.add(totalFee);

        //  更新
        entity.setPurchaseQuantity(req.getQuantity());
        entity.setAccount(req.getAccount());
        entity.setBaseAmount(baseAmount);
        entity.setTotalFee(totalFee);
        entity.setTotalAmount(totalAmount);


        likeListRepository.save(entity);
    }

}