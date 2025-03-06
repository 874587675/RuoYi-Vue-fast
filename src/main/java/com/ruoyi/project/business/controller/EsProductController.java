package com.ruoyi.project.business.controller;
import com.ruoyi.project.business.domain.EsProduct;
import com.ruoyi.project.business.service.impl.EsProductServiceImpl;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import io.swagger.annotations.Api;  
import io.swagger.annotations.ApiOperation;  
/**
* @author zgc
*/
@Api(tags = "")
@RestController
@RequestMapping("/t_es_product")
public class EsProductController {

    @Resource
    private EsProductService esProductService;
}
