package com.ruoyi.project.business.verify.wechat.pay.pc.paybusiness.service.impl;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.verify.config.WxPayConfig;
import com.ruoyi.common.verify.wechat.util.WxPayUtil;
import com.ruoyi.common.verify.wechat.vo.WeChatPayVO;
import com.ruoyi.framework.redis.RedisCache;
import com.ruoyi.project.business.verify.wechat.pay.pc.paybusiness.common.WxPayCommon;
import com.ruoyi.project.business.verify.wechat.pay.pc.paybusiness.service.WxPayment;
import com.wechat.pay.java.core.Config;
import com.wechat.pay.java.service.payments.app.AppServiceExtension;
import com.wechat.pay.java.service.payments.app.model.Amount;
import com.wechat.pay.java.service.payments.app.model.PrepayWithRequestPaymentResponse;
import com.wechat.pay.java.service.payments.model.Transaction;
import com.wechat.pay.java.service.refund.model.Refund;
import com.wechat.pay.java.service.refund.model.RefundNotification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @ClassName:WxPayAppService
 * @description:
 * @author: zgc
 **/
@Slf4j
@Service
public class WxPayAppService implements WxPayment {
    @Resource
    private WxPayConfig wxPayConfig;
    @Resource
    private WxPayUtil wxPayUtil;
    @Resource
    private RedisCache redisCache;
    
    @Override
    public Map<String, String> wxPay(WeChatPayVO weChatPayVO) throws Exception {
        log.info("APP下单-开始");
        Map<String, String> oldPayReturnMap = redisCache.getCacheObject(WxPayCommon.getWxPayCacheKey(weChatPayVO));
        if (oldPayReturnMap != null) {
            return oldPayReturnMap;
        }
        Config config = wxPayConfig.getWxPayConfig();
        // 构建service
        AppServiceExtension service = new AppServiceExtension.Builder().config(config).build();
        //构建请求
        com.wechat.pay.java.service.payments.app.model.PrepayRequest request = new com.wechat.pay.java.service.payments.app.model.PrepayRequest();
        //基础信息
        request.setAppid(weChatPayVO.getAppId());
        request.setMchid(weChatPayVO.getMchId());
        request.setDescription(weChatPayVO.getDescription());//商品描述
        request.setOutTradeNo(weChatPayVO.getOutTradeNo());//商户系统内部订单号
        request.setNotifyUrl(weChatPayVO.getNotifyUrl());
//        request.setAttach(attach);//附加数据
        //订单金额信息
        Amount amount = new Amount();
        amount.setTotal(1);
        request.setAmount(amount);
        try {
            PrepayWithRequestPaymentResponse response = service.prepayWithRequestPayment(request);
            log.info("APP下单-结束：{}", JSON.toJSONString(response));
            Map<String, String> payReturnMap = wxPayUtil.getPayReturnMap(response);
            // 将支付信息存入数据库
            redisCache.setCacheObject(WxPayCommon.getWxPayCacheKey(weChatPayVO), payReturnMap);
            return payReturnMap;
        } catch (Exception ex) {
            throw new ServiceException("APP下单-失败");
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
