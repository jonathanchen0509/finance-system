package com.example.finance.service;

import com.example.finance.dto.LikeListRequest;
import com.example.finance.dto.LikeListResponse;
import com.example.finance.entity.LikeList;
import com.example.finance.repository.LikeListRepository;
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

    @Transactional
    public void addLike(LikeListRequest req) {
        likeListRepository.callAddLikeSP(
                req.getUserId(),
                req.getProductId(),
                req.getQuantity(),
                req.getAccount()
        );
    }
    public LikeListResponse getLikeList(String userId) {

        List<Object[]> rows = likeListRepository.findLikeListByUserId(userId);

        LikeListResponse response = new LikeListResponse();

        BigDecimal totalAmount = BigDecimal.ZERO;
        BigDecimal totalFee = BigDecimal.ZERO;

        List<LikeListResponse.ProductItem> items = new ArrayList<>();

        for (Object[] row : rows) {

            // 使用者資訊（只設定一次）
            if (response.getUserName() == null) {
                response.setUserName((String) row[0]);
                response.setEmail((String) row[1]);
                response.setAccount((String) row[2]);
            }

            // 商品資料
            LikeListResponse.ProductItem item = new LikeListResponse.ProductItem();
            item.setProductName((String) row[3]);
            item.setQuantity((Integer) row[4]);
            item.setTotalAmount((BigDecimal) row[5]);
            item.setTotalFee((BigDecimal) row[6]);

            totalAmount = totalAmount.add(item.getTotalAmount());
            totalFee = totalFee.add(item.getTotalFee());

            items.add(item);
        }

        response.setTotalAmount(totalAmount);
        response.setTotalFee(totalFee);
        response.setProducts(items);

        return response;
    }

    @Transactional
    public void deleteLike(Integer sn) {
        likeListRepository.deleteById(sn);
    }

    @Transactional
    public void updateLike(Integer sn, LikeListRequest req) {

        LikeList entity = likeListRepository.findById(sn)
                .orElseThrow(() -> new RuntimeException("找不到資料"));

        entity.setPurchaseQuantity(req.getQuantity());
        entity.setAccount(req.getAccount());

        likeListRepository.save(entity);
    }

}