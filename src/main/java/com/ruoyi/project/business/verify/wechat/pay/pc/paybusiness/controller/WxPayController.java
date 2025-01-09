package com.ruoyi.project.business.verify.wechat.pay.pc.paybusiness.controller;

import com.ruoyi.common.verify.wechat.vo.QueryOrderVO;
import com.ruoyi.common.verify.wechat.vo.WeChatPayVO;
import com.ruoyi.framework.web.domain.R;
import com.ruoyi.project.business.domain.Order;
import com.ruoyi.project.business.verify.wechat.pay.pc.paybusiness.service.impl.WxPayH5Service;
import com.ruoyi.project.business.verify.wechat.pay.pc.paybusiness.service.impl.WxPayJsapiService;
import com.ruoyi.project.business.verify.wechat.pay.pc.paybusiness.service.impl.WxpayNativeService;
import com.wechat.pay.java.service.payments.model.Transaction;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/pay/wxpay")
@Api(tags = "微信支付管理模块")
public class WxPayController {
    @Resource
    private WxPayJsapiService wxPayJsapiService;
    @Resource
    private WxpayNativeService wxpayNativeService;
    @Resource
    private WxPayH5Service wxPayH5Service;
    
    
    @PostMapping("/native/toWxPay")
    @ApiOperation("微信Native环境支付订单")
    public R<Map<String, String>> toWxPayByNative(@RequestBody Order order) {
        return R.ok(wxpayNativeService.toWxPayByNative(order));
    }
    
    @PostMapping("/jsapi/toWxPay")
    @ApiOperation("微信JSAPI环境支付订单")
    public R<Map<String, String>> toWxPayByJsapi(@RequestBody WeChatPayVO weChatPayVO) throws Exception {
        return R.ok(wxPayJsapiService.wxPay(weChatPayVO));
    }

    @PostMapping("/h5/toWxPay")
    @ApiOperation("微信H5环境支付订单")
    public R<Map<String, String>> toWxPayByH5(@RequestBody WeChatPayVO weChatPayVO) throws Exception {
        return R.ok(wxPayH5Service.wxPay(weChatPayVO));
    }
    
    @GetMapping("queryOrderByTransactionId")
    @ApiOperation("通过微信订单号查询订单")
    public R<Transaction> queryOrderByTransactionId(@RequestParam String transactionId) throws Exception {
        // 调用微信支付查询接口
        return R.ok(wxPayJsapiService.queryOrderByTransactionId(transactionId));
    }

    @GetMapping("queryOrderByOutTradeNo")
    @ApiOperation("通过微信订单号查询订单")
    public R<Transaction> queryOrderByOutTradeNo(@RequestParam String outTradeNo) throws Exception {
        // 调用微信支付查询接口
        return R.ok(wxPayJsapiService.queryOrderByOutTradeNo(outTradeNo));
    }
}
