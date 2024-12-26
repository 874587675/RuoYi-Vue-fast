package com.ruoyi.project.business.controller;

import com.ruoyi.project.business.service.impl.OrderServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@Api(tags = "订单管理模块")
public class OrderController {

    @Autowired
    private OrderServiceImpl orderService;
    
    
}
