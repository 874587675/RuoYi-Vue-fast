package com.ruoyi.project.business.verify.aliyun.pay.pc.paynotify.controller;

import com.ruoyi.common.verify.aliyun.pay.pc.paynotify.service.AlipayNotifyService;
import com.ruoyi.framework.web.domain.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequestMapping("/api/pay/alipay")
@Api(tags = "支付宝支付通知")
@RestController
public class AlipayNotifyController {
    @Resource
    private AlipayNotifyService alipayNotifyService;
    
    @PostMapping("/notify")
    @ApiOperation("支付宝支付通知")
    private R<String> notify(HttpServletRequest request, HttpServletResponse response) {
        try {
            boolean isVerified = alipayNotifyService.verifyNotify(request);
            if (isVerified) {
                // 支付成功，返回200状态码
                return R.ok("支付成功");
            } else {
                // 验证失败，返回500状态码
                return R.fail("支付失败");
            }
        } catch (Exception e) {
            return R.fail("支付失败");
        }
    }
}
