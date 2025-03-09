package com.ruoyi.project.business.es.service;

import com.ruoyi.project.business.es.domain.ProductElasticsearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ProductElasticsearchRepository extends ElasticsearchRepository<ProductElasticsearch, String> {
    // 可以定义根据 Elasticsearch 提供的查询支持自定义查询方法
    List<ProductElasticsearch> findByNameContaining(String name);
}
