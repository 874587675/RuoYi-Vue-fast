package com.ruoyi.project.business.controller;
import com.ruoyi.project.business.domain.UserReal;
import com.ruoyi.project.business.service.UserRealService;
import com.ruoyi.project.business.service.impl.UserRealServiceImpl;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/userReal")
public class UserRealController {

    @Autowired
    private UserRealService userRealService;
    
}
