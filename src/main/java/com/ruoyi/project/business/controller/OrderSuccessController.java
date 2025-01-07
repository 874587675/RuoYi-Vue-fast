package com.ruoyi.project.business.controller;
import com.ruoyi.project.business.domain.OrderSuccess;
import com.ruoyi.project.business.service.OrderSuccessService;
import com.ruoyi.project.business.service.impl.OrderSuccessServiceImpl;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/orderSuccess")
public class OrderSuccessController {

    @Autowired
    private OrderSuccessService orderSuccessServiceImpl;



}
