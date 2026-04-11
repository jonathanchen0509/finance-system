package com.example.finance.controller;

import com.example.finance.dto.LikeListRequest;
import com.example.finance.dto.LikeListResponse;
import com.example.finance.service.LikeListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/likes")
@RequiredArgsConstructor
public class LikeListController {

    private final LikeListService likeListService;

    @PostMapping
    public String addLike(@RequestBody LikeListRequest request) {
        likeListService.addLike(request);
        return "新增成功";
    }
    @GetMapping("/{userId}")
    public LikeListResponse getLikeList(@PathVariable String userId) {
        return likeListService.getLikeList(userId);
    }

    @DeleteMapping("/{sn}")
    public String deleteLike(@PathVariable Integer sn) {
        likeListService.deleteLike(sn);
        return "刪除成功";
    }

    @PutMapping("/{sn}")
    public String updateLike(@PathVariable Integer sn,
                             @RequestBody LikeListRequest request) {
        likeListService.updateLike(sn, request);
        return "更新成功";
    }
}

