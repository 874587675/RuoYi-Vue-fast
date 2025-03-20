//package com.ruoyi.project.business.service.impl;
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
//import com.ruoyi.project.business.domain.Product;
//import com.ruoyi.project.business.es.domain.ProductElasticsearch;
//import com.ruoyi.project.business.es.service.ProductElasticsearchRepository;
//import com.ruoyi.project.business.mapper.ProductMapper;
//import com.ruoyi.project.business.service.ProductService;
//import org.elasticsearch.index.query.FuzzyQueryBuilder;
//import org.elasticsearch.index.query.QueryBuilder;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
//import org.springframework.data.elasticsearch.core.SearchHits;
//import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//import java.util.List;
//import java.util.stream.Collectors;
//
///**
//* @program: RuoYi-Vue-fast
//* @ClassName EsProductServiceImpl
//* @description:
//* @author: zgc
//* @date: 2025-03-06 18:16
//* @Version 1.0
//**/
//@Service
//public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
//    @Resource
//    private ProductMapper productMapper;
//
//    @Resource
//    private ProductElasticsearchRepository productElasticsearchRepository;
//
//    @Resource
//    private ElasticsearchRestTemplate elasticsearchRestTemplate;
//
//    // 将数据库中的产品数据同步到 Elasticsearch
//    @Override
//    public void syncProductsToElasticsearch() {
//        List<Product> products = productMapper.selectList(new QueryWrapper<>());
//        List<ProductElasticsearch> elasticsearchProducts = products.stream()
//                .map(product -> {
//                    ProductElasticsearch elasticsearchProduct = new ProductElasticsearch();
//                    elasticsearchProduct.setId(String.valueOf(product.getId()));
//                    elasticsearchProduct.setName(product.getName());
//                    elasticsearchProduct.setPrice(product.getPrice());
//                    return elasticsearchProduct;
//                })
//                .collect(Collectors.toList());
//        productElasticsearchRepository.saveAll(elasticsearchProducts);
//    }
//
//    @Override
//    public List<ProductElasticsearch> searchProducts(String query) {
//        return productElasticsearchRepository.findByNameContaining(query);  // 在 Elasticsearch 中进行全文检索
//    }
//
//    public SearchHits<ProductElasticsearch> searchLikeProducts(String queryString) {
//        // 使用 QueryBuilders 创建查询条件
//        NativeSearchQuery searchQuery = new NativeSearchQuery(
//                QueryBuilders.queryStringQuery(queryString)  // 转换为 Spring Data Elasticsearch 支持的 Query 类型
//        );
//
//        // 执行查询并返回结果
//        return elasticsearchRestTemplate.search(searchQuery, ProductElasticsearch.class);
//    }
//
//    public SearchHits<ProductElasticsearch> searchFuzzyQuery(String queryString) {
//        // 通过 QueryBuilders 创建实际的查询条件
//        QueryBuilder query = QueryBuilders.fuzzyQuery("name", queryString);
//
//        NativeSearchQuery searchQuery = new NativeSearchQuery(query);
//        // 执行查询，获取搜索结果
//        // 假设你在搜索 `Product` 类型的文档
//        return elasticsearchRestTemplate.search(searchQuery, ProductElasticsearch.class);
//    }
//}
