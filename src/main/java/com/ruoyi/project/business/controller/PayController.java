package com.ruoyi.project.business.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.verify.config.AlipayConfigs;
import com.ruoyi.common.verify.config.WxPayConfig;
import com.ruoyi.project.business.domain.Order;
import com.wechat.pay.java.core.Config;
import com.wechat.pay.java.service.payments.nativepay.NativePayService;
import com.wechat.pay.java.service.payments.nativepay.model.Amount;
import com.wechat.pay.java.service.payments.nativepay.model.PrepayRequest;
import com.wechat.pay.java.service.payments.nativepay.model.PrepayResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/pay")
@Api(tags = "支付管理模块")
public class PayController {
    @Resource
    private AlipayConfigs aliPayConfigs;
    
    @Resource
    private WxPayConfig wxPayConfig;
    
    @PostMapping("/alipay/pc/toAliPay")
    @ApiOperation("支付宝网页环境支付订单")
    public String toAliPay(Order order) throws AlipayApiException {
        AlipayClient aliPayClient = new DefaultAlipayClient(aliPayConfigs.getAlipayConfig());
        // 设置支付请求模型
        AlipayTradePagePayModel model = new AlipayTradePagePayModel();

        model.setOutTradeNo(order.getOrderNo());    // 商户订单号 必选
        model.setTotalAmount(String.valueOf(order.getAmount()));    // 订单金额 必选
        model.setSubject(order.getSubject());   // 订单标题 必选
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

    @PostMapping("/wxpay/pc/toWxPay")
    @ApiOperation("微信网页环境支付订单")
    public void toWxPay(Order order) {
        Config config = wxPayConfig.getWxPayConfig();

        NativePayService service = new NativePayService.Builder().config(config).build();

        // request.setXxx(val)设置所需参数，具体参数可见Request定义
        PrepayRequest request = new PrepayRequest();

        Amount amount = new Amount();
        amount.setTotal(100);
        request.setAmount(amount);
        request.setAppid("wxff0cf54e2af1fa40");
        request.setMchid("1606444287");
        request.setDescription("测试商品标题");
        request.setNotifyUrl("https://notify_url");
        request.setOutTradeNo("out_trade_no_001");

        PrepayResponse prepay = service.prepay(request);

        System.out.println(prepay.getCodeUrl());
    }
    
    
}
