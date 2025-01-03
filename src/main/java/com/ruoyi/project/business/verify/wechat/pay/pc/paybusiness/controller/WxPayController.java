package com.ruoyi.project.business.verify.wechat.pay.pc.paybusiness.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.verify.config.AlipayConfigs;
import com.ruoyi.common.verify.config.WxPayConfig;
import com.ruoyi.framework.web.domain.R;
import com.ruoyi.project.business.domain.Order;
import com.ruoyi.project.business.verify.wechat.pay.pc.paybusiness.service.WxPayJsapiService;
import com.wechat.pay.java.core.Config;
import com.wechat.pay.java.service.payments.jsapi.JsapiService;
import com.wechat.pay.java.service.payments.jsapi.model.Payer;
import com.wechat.pay.java.service.payments.nativepay.NativePayService;
import com.wechat.pay.java.service.payments.nativepay.model.Amount;
import com.wechat.pay.java.service.payments.nativepay.model.PrepayRequest;
import com.wechat.pay.java.service.payments.nativepay.model.PrepayResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/pay/wxpay")
@Api(tags = "微信支付管理模块")
public class WxPayController {
    @Resource
    private WxPayJsapiService wxPayJsapiService;
    
    
    @PostMapping("/native/toWxPay")
    @ApiOperation("微信Native网页环境支付订单")
    public void toWxPayByNative() {
//        Config config = wxPayConfig.getWxPayConfig();
//        NativePayService service = new NativePayService.Builder().config(config).build();
//        
//        PrepayRequest request = new PrepayRequest();
//        Amount amount = new Amount();
//        amount.setTotal(100);
//        request.setAmount(amount);
//        request.setAppid(wxPayConfig.getPayparams().getAppId());
//        request.setMchid(wxPayConfig.getPayparams().getMerchantId());
//        request.setDescription("测试商品标题");
//        request.setNotifyUrl("https://notify_url");
//        request.setOutTradeNo("out_trade_no_001");
//        
//        PrepayResponse prepay = service.prepay(request);
//        System.out.println(prepay.getCodeUrl());
    }



    @PostMapping("/jsapi/toWxPay")
    @ApiOperation("微信Native网页环境支付订单")
    public R<Map<String, String>> toWxPayByJsapi(@RequestBody Order order) throws Exception {
        return R.ok(wxPayJsapiService.toWxPayByJsapi(order));
    }
}
