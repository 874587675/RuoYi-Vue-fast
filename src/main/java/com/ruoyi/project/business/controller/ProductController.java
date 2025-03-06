package com.ruoyi.project.business.controller;


import com.ruoyi.project.business.service.ProductService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;  
/**
* @author zgc
*/
@Api(tags = "")
@RestController
@RequestMapping("/t_product")
public class ProductController {

    @Resource
    private ProductService productService;
}
