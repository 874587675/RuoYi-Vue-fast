package com.ruoyi.project.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.project.business.domain.Product;
import com.ruoyi.project.business.es.domain.ProductElasticsearch;
import org.springframework.data.elasticsearch.core.SearchHits;

import java.util.List;

/**
* @program: RuoYi-Vue-fast
* @ClassName EsProductService
* @description: 
* @author: zgc
* @date: 2025-03-06 18:16
* @Version 1.0
**/
public interface ProductService extends IService<Product>{
    void syncProductsToElasticsearch();

    List<ProductElasticsearch> searchProducts(String query);

    SearchHits<ProductElasticsearch> searchLikeProducts(String queryString);

    SearchHits<ProductElasticsearch> searchFuzzyQuery(String queryString);
}
