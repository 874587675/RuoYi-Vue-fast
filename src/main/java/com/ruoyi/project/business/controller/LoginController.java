package com.ruoyi.project.business.controller;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.business.domain.User;
import com.ruoyi.project.business.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program:
 * @ClassName:
 * @description:
 * @author: zgc
 * @date:
 * @Version 1.0
 **/
@RestController
@RequestMapping("/login")
@Api(tags = "登录注册模块")
public class LoginController {
    @Autowired
    private UserService userService;
    
    @PostMapping("/login")
    public AjaxResult login(@RequestBody User user) {
        AjaxResult ajax = AjaxResult.success();
        System.out.println("接入了");
        String token = userService.login(user.getUsername(), user.getPassword());
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }
}

