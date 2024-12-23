package com.ruoyi.project.business.controller;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.verify.sms.aliyun.SendSmsService;
import com.ruoyi.framework.web.domain.R;
import com.ruoyi.project.business.service.UserService;
import com.ruoyi.framework.security.LoginBody;
import com.ruoyi.framework.web.domain.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;

@Api(tags = "登录注册模块")
@RestController
@RequestMapping("/login")
public class LoginController {
    @Resource
    private UserService userService;
    @Resource
    private SendSmsService sendSmsService;

    @ApiOperation("用户名密码登录")
    @PostMapping("/loginByUsername")
    public AjaxResult loginByUsername(@RequestBody LoginBody loginBody) {
        AjaxResult ajax = AjaxResult.success();
        String token = userService.loginByUsername(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
                loginBody.getUuid());   // 生成令牌
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }

    @ApiOperation("手机号验证码登录")
    @PostMapping("/loginByPhone")
    public AjaxResult loginByPhone(@RequestBody LoginBody loginBody) throws ExecutionException, InterruptedException {
        AjaxResult ajax = AjaxResult.success();
        String token = userService.loginByPhone(loginBody.getPhone(), loginBody.getCode());// 生成令牌
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }

    @ApiOperation("获取手机验证码")
    @GetMapping("/getPhoneCode")
    public R<String> getPhoneCode(@RequestParam String phone) throws ExecutionException, InterruptedException {
        Boolean flag = sendSmsService.SendPhoneCodeToLoginOrRegister(phone);
        if (flag) return R.ok("获取验证码成功");
        else return R.fail("获取验证码失败");
    }
}
