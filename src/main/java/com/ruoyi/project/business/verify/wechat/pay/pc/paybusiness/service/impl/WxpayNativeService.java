package com.ruoyi.project.business.verify.wechat.pay.pc.paybusiness.service.impl;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.verify.config.WxPayConfig;
import com.ruoyi.common.verify.wechat.util.WxPayUtil;
import com.ruoyi.common.verify.wechat.vo.WeChatPayVO;
import com.ruoyi.project.business.verify.wechat.pay.pc.paybusiness.service.WxPayment;
import com.wechat.pay.java.core.Config;
import com.wechat.pay.java.service.payments.model.Transaction;
import com.wechat.pay.java.service.payments.nativepay.NativePayService;
import com.wechat.pay.java.service.payments.nativepay.model.Amount;
import com.wechat.pay.java.service.payments.nativepay.model.PrepayResponse;
import com.wechat.pay.java.service.refund.model.Refund;
import com.wechat.pay.java.service.refund.model.RefundNotification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @ClassName: WxpayNativeService
 * @description: 微信支付native方式
 * @author: zgc
 **/
@Service
@Slf4j
public class WxpayNativeService implements WxPayment {

    @Resource
    private WxPayConfig wxPayConfig;
    @Resource
    private WxPayUtil wxPayUtil;

    @Override
    public Map<String, String> wxPay(WeChatPayVO weChatPayVO) throws Exception {
        log.info("Native下单(扫码支付)-开始");
        Config config = wxPayConfig.getWxPayConfig();
        // 构建service
        NativePayService service = new NativePayService.Builder().config(config).build();
        //构建请求
        com.wechat.pay.java.service.payments.nativepay.model.PrepayRequest request = new com.wechat.pay.java.service.payments.nativepay.model.PrepayRequest();
        //基础信息
        request.setAppid(wxPayConfig.getPayparams().getAppId());
        request.setMchid(wxPayConfig.getPayparams().getMerchantId());
        request.setDescription(weChatPayVO.getDescription());//商品描述
        request.setOutTradeNo(weChatPayVO.getOutTradeNo());//商户系统内部订单号
        request.setNotifyUrl(wxPayConfig.getPayparams().getNotifyUrl());
        //订单金额信息
        Amount amount = new Amount();
        amount.setTotal(1);
        request.setAmount(amount);
        try {
            PrepayResponse response = service.prepay(request);
            log.info("Native下单(扫码支付)-结束：{}", JSON.toJSONString(response));
            Map<String, String> payReturnMap = wxPayUtil.getPayReturnMap(response);
            return payReturnMap;
        } catch (Exception ex) {
            throw new ServiceException("Native下单(扫码支付)-异常");
        }
    }

    @Override
    public Transaction queryOrderByTransactionId(String transactionId) throws Exception {
        return null;
    }

    @Override
    public Transaction queryOrderByOutTradeNo(String outTradeNo) throws Exception {
        return null;
    }

    @Override
    public Boolean closeByOutTradeNo(String outTradeNo) {
        return null;
    }

    @Override
    public Boolean refundsByOutTradeNo(String outTradeNo, String outRefundNo, Integer total, Integer refund) {
        return null;
    }

    @Override
    public Boolean refundsByTransactionId(String transactionId, String outRefundNo, Integer total, Integer refund) {
        return null;
    }

    @Override
    public Refund refundsQueryByOutRefundNo(String outRefundNo) {
        return null;
    }

    @Override
    public Transaction notify(HttpServletRequest request) {
        return null;
    }

    @Override
    public RefundNotification refundNotify(HttpServletRequest request) {
        return null;
    }
}
