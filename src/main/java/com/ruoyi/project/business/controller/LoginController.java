package com.ruoyi.project.business.controller;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.verify.aliyun.sms.SmsUtil;
import com.ruoyi.framework.security.LoginBody;
import com.ruoyi.framework.security.RegisterBody;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.domain.R;
import com.ruoyi.project.business.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;


@RestController
@RequestMapping("/index")
@Api(tags = "登录注册管理模块")
public class LoginController {
    @Resource
    private UserService userService;

    @ApiOperation("用户名密码验证码登录")
    @PostMapping("/loginByUsername")
    public AjaxResult loginByUsername(@RequestBody LoginBody loginBody) {
        AjaxResult ajax = AjaxResult.success();
        String token = userService.loginByUsername(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
                loginBody.getUuid());   // 生成令牌
        ajax.put("code",200);
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }

}
