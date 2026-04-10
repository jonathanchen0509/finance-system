package com.example.finance.controller;

import com.example.finance.entity.LikeList;
import com.example.finance.service.LikeListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/likes")
public class LikeListController {

    private final LikeListService likeListService;

    public LikeListController(LikeListService likeListService) {
        this.likeListService = likeListService;
    }

    //  新增
    @PostMapping
    public LikeList create(
            @RequestParam Long productId,
            @RequestParam int quantity,
            @RequestParam String account) {

        return likeListService.create(productId, quantity, account);
    }

    //  查詢全部
    @GetMapping
    public List<LikeList> getAll() {
        return likeListService.getAll();
    }

    //  刪除
    @DeleteMapping("/{sn}")
    public void delete(@PathVariable Long sn) {
        likeListService.delete(sn);
    }

    //  更新
    @PutMapping("/{sn}")
    public LikeList update(
            @PathVariable Long sn,
            @RequestParam Long productId,
            @RequestParam int quantity,
            @RequestParam String account) {

        return likeListService.update(sn, productId, quantity, account);
    }
}