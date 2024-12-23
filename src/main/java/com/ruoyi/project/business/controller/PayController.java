package com.ruoyi.project.business.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;

import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.verify.config.AlipayConfig;
import com.ruoyi.project.business.domain.Order;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/pay")
@Api(tags = "支付管理")
public class PayController {
    @Resource
    private AlipayConfig aliPayConfig;
    
    @PostMapping("/toPay")
    @ApiOperation("支付宝网页环境支付订单")
    public String toPay(Order order) throws AlipayApiException {
        AlipayClient aliPayClient = new DefaultAlipayClient(aliPayConfig.getAlipayConfig());
        
        // 设置支付请求模型
        AlipayTradePagePayModel model = new AlipayTradePagePayModel();

        model.setOutTradeNo(order.getOrderNo());
        model.setTotalAmount(String.valueOf(order.getAmount()));
        model.setSubject(order.getSubject());
        model.setProductCode(aliPayConfig.getPayparams().getProductCode());
        
        model.setQrPayMode("2");
        // 请求支付
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        request.setReturnUrl(aliPayConfig.getPayparams().getReturnUrl());
        request.setNotifyUrl(aliPayConfig.getPayparams().getNotifyUrl());
        request.setBizModel(model);
        
        AlipayTradePagePayResponse response = aliPayClient.pageExecute(request);

        if (response.isSuccess()) {
            return response.getBody();  // 返回支付页面 HTML
        } else {
            throw new ServiceException("支付宝支付请求失败");
        }
    }
}
