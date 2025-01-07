package com.ruoyi.project.business.verify.wechat.pay.pc.paynotify.controller;


import com.ruoyi.framework.web.domain.R;
import com.ruoyi.project.business.verify.wechat.pay.pc.paynotify.service.WxpayNotifyService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequestMapping("/api/pay/wx")
@ApiOperation("微信支付通知")
@RestController
public class WxpayNotifyController {
   
    @Resource
    private WxpayNotifyService wxpayNotifyService;
    
    @PostMapping("/wxnotify")
    public R<String> wxnotify(HttpServletRequest request) throws IOException {
        return R.ok(wxpayNotifyService.wxnotify(request));
    }
}
