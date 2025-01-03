package com.ruoyi.common.verify.aliyun.pay.pc.payreturn.controller;

import com.ruoyi.common.verify.aliyun.pay.pc.payreturn.service.AlipayReturnService;
import com.ruoyi.framework.web.domain.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequestMapping("/api/pay")
@ApiOperation("支付宝支付回调")
@RestController
public class AlipayReturnController {
    @Resource
    private AlipayReturnService alipayReturnService;
    
    @RequestMapping("/return")
    public R<String> returnUrl(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        alipayReturnService.returnUrl(request,response);
        return R.ok("回调成功");
    }
}
