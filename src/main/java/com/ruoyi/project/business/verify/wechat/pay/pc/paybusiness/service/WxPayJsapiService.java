package com.ruoyi.project.business.verify.wechat.pay.pc.paybusiness.service;

import com.ruoyi.common.utils.uuid.UUID;
import com.ruoyi.common.verify.config.WxPayConfig;
import com.ruoyi.common.verify.wechat.util.WxPayUtil;
import com.ruoyi.project.business.domain.Order;
import com.wechat.pay.java.core.Config;
import com.wechat.pay.java.service.payments.jsapi.JsapiService;
import com.wechat.pay.java.service.payments.jsapi.model.Amount;
import com.wechat.pay.java.service.payments.jsapi.model.Payer;
import com.wechat.pay.java.service.payments.jsapi.model.PrepayRequest;
import com.wechat.pay.java.service.payments.jsapi.model.PrepayResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName:WxPayJsapiService
 * @description:
 * @author: zgc
 **/
@Service
@Slf4j
public class WxPayJsapiService {
    @Resource
    private WxPayConfig wxPayConfig;
    @Resource
    private WxPayUtil wxPayUtil;
    
    public Map<String,String> toWxPayByJsapi(Order order) throws Exception {
        Config config = wxPayConfig.getWxPayConfig();
        
        JsapiService service = new JsapiService.Builder().config(config).build();

        PrepayRequest request = new PrepayRequest();
        Amount amount = new Amount();
        amount.setTotal(order.getAmount().multiply(new BigDecimal(100)).intValue());
        request.setAmount(amount);
        request.setAppid(wxPayConfig.getPayparams().getAppId());
        request.setMchid(wxPayConfig.getPayparams().getMerchantId());
        request.setDescription("测试商品标题");
        request.setNotifyUrl(wxPayConfig.getPayparams().getNotifyUrl());
        request.setOutTradeNo(order.getOrderNo());
        Payer payer = new Payer();
        payer.setOpenid("ou6YH41g7ceN1XECAoVaCgKeYAco");
        request.setPayer(payer);
        PrepayResponse prepayResponse = service.prepay(request);
        System.out.println(prepayResponse);
        
        // 获取返回的prepay_id
        String prepayId = prepayResponse.getPrepayId();

        // 获取当前时间戳和随机字符串，生成签名所需参数
        long timeStamp = System.currentTimeMillis() / 1000; // 微信支付要求的时间戳，单位是秒
        String nonceStr = UUID.fastUUID().toString(true); // 随机字符串，生成签名时需要用到

        // 构建支付请求的参数
        Map<String, String> payParams = new HashMap<>();
        payParams.put("appId", wxPayConfig.getPayparams().getAppId());
        payParams.put("timeStamp", String.valueOf(timeStamp));
        payParams.put("nonceStr", nonceStr);
        payParams.put("package", "prepay_id=" + prepayId);
        payParams.put("signType", "RSA");
        //生成签名
        String paySign = wxPayUtil.generateSignature(payParams, wxPayConfig.getPayparams().getPrivateKeyPath());
        payParams.put("paySign", paySign);
        // 返回支付参数
        return payParams;
        
    }
}
