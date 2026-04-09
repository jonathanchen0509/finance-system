package com.example.finance.controller;

import com.example.finance.entity.LikeList;
import com.example.finance.service.LikeListService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes")
public class LikeListController {

    private final LikeListService likeListService;

    public LikeListController(LikeListService likeListService) {
        this.likeListService = likeListService;
    }

    @PostMapping
    public LikeList create(
            @RequestParam String userId,
            @RequestParam Long productId,
            @RequestParam int quantity) {

        return likeListService.create(userId, productId, quantity);
    }
}