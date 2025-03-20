//package com.ruoyi.project.business.controller;
//
//
//import com.ruoyi.project.business.domain.Product;
//import com.ruoyi.project.business.es.domain.ProductElasticsearch;
//import com.ruoyi.project.business.mapper.ProductMapper;
//import com.ruoyi.project.business.service.ProductService;
//import io.swagger.annotations.Api;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.elasticsearch.core.SearchHits;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
///**
//* @author zgc
//*/
//@Api(tags = "")
//@RestController
//@RequestMapping("/product")
//public class ProductController {
//
//    @Autowired
//    private ProductService productService;
//
//    @Autowired
//    private ProductMapper productMapper;
//
//    // 增加产品并同步到 Elasticsearch
//    @PostMapping
//    public Product addProduct(@RequestBody Product product) {
//        productMapper.insert(product);  // 保存到数据库
//        productService.syncProductsToElasticsearch();  // 同步到 Elasticsearch
//        return product;
//    }
//
//    // 获取所有产品
//    @GetMapping
//    public List<Product> getAllProducts() {
//        return productMapper.selectList(null);  // 获取数据库中的所有产品
//    }
//
//    // 全文检索产品
//    @GetMapping("/search")
//    public List<ProductElasticsearch> searchProducts(@RequestParam String query) {
//        return productService.searchProducts(query);  // 在 Elasticsearch 中进行全文检索
//    }
//
//    // 手动同步数据库数据到 Elasticsearch
//    @GetMapping("/sync")
//    public String syncProductsToElasticsearch() {
//        productService.syncProductsToElasticsearch();
//        return "Synchronization Complete!";
//    }
//
//    //分词查询
//    @GetMapping("/searchLike")
//    public SearchHits<ProductElasticsearch> searchLikeProducts(@RequestParam String query) {
//        return productService.searchLikeProducts(query);  // 在 Elasticsearch 中进行全文检索
//    }
//
//    @GetMapping("/searchFuzzy")
//    public SearchHits<ProductElasticsearch> searchFuzzyQuery(@RequestParam String query) {
//        return productService.searchFuzzyQuery(query);  // 在 Elasticsearch 中进行全文检索
//    }
//
//}
