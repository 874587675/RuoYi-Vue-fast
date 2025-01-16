package com.ruoyi.project.business.verify.aliyun.pay.pc.paybusiness.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.verify.config.AlipayConfigs;
import com.ruoyi.project.business.verify.aliyun.pay.pc.paybusiness.service.AliPayment;
import com.ruoyi.project.business.verify.aliyun.pay.vo.AliPayPayVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @program: RuoYi-Vue-fast
 * @ClassName AliPayPCService
 * @description:
 * @author: zgc
 * @date: 2025-01-15 22:21
 * @Version 1.0
 **/
@Service
public class AliPayPCService implements AliPayment {

    @Resource
    private AlipayConfigs aliPayConfigs;

    @Override
    public String pay(AliPayPayVO aliPayPayVO) throws AlipayApiException {
        AlipayClient aliPayClient = new DefaultAlipayClient(aliPayConfigs.getAlipayConfig());
        // 设置支付请求模型
        AlipayTradePagePayModel model = new AlipayTradePagePayModel();
        model.setOutTradeNo(aliPayPayVO.getOutTradeNo());    // 商户订单号 必选
        model.setTotalAmount(String.valueOf(aliPayPayVO.getTotalAmount()));    // 订单金额 必选
        model.setSubject(aliPayPayVO.getSubject());   // 订单标题 必选
        model.setProductCode(aliPayConfigs.getPayparams().getProductCode());   // 销售产品码 必选   

        model.setQrPayMode("2");    // 跳转模式 可选
        // 请求支付
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        request.setReturnUrl(aliPayConfigs.getPayparams().getReturnUrl());
        request.setNotifyUrl(aliPayConfigs.getPayparams().getNotifyUrl());
        request.setBizModel(model);

        AlipayTradePagePayResponse response = aliPayClient.pageExecute(request);
        if (response.isSuccess()) {
            return response.getBody();  // 返回支付页面 HTML
        } else {
            throw new ServiceException("支付宝支付请求失败");
        }
    }
}

