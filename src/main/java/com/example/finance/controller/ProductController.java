package com.example.finance.controller;

import com.example.finance.entity.Product;
import com.example.finance.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // 新增商品
    @PostMapping
    public Product create(@RequestBody Product product) {
        return productRepository.save(product);
    }

    // 查詢全部商品
    @GetMapping
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    // 刪除商品
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productRepository.deleteById(id);
    }

    // 更新商品
    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product product) {
        product.setNo(id);
        return productRepository.save(product);
    }


}