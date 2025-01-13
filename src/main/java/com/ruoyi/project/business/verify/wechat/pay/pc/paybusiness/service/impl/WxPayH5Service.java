package com.ruoyi.project.business.verify.wechat.pay.pc.paybusiness.service.impl;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.verify.config.WxPayConfig;
import com.ruoyi.common.verify.wechat.util.WxPayUtil;
import com.ruoyi.common.verify.wechat.vo.WeChatPayVO;
import com.ruoyi.framework.redis.RedisCache;
import com.ruoyi.project.business.verify.wechat.pay.pc.paybusiness.service.WxPayment;
import com.wechat.pay.java.core.RSAAutoCertificateConfig;
import com.wechat.pay.java.service.payments.h5.H5Service;
import com.wechat.pay.java.service.payments.h5.model.*;
import com.wechat.pay.java.service.payments.model.Transaction;
import com.wechat.pay.java.service.refund.model.Refund;
import com.wechat.pay.java.service.refund.model.RefundNotification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @ClassName:WxPayH5Service
 * @description:
 * @author: zgc
 **/
@Service
@Slf4j
public class WxPayH5Service implements WxPayment {
    @Resource
    private WxPayConfig wxPayConfig;
    @Resource
    private WxPayUtil wxPayUtil;
    @Resource
    private RedisCache redisCache;


    @Override
    public Map<String, String> wxPay(WeChatPayVO weChatPayVO) throws Exception {
        log.info("WeChatPayVO: {}" , weChatPayVO);
        log.info("H5下单-开始");
//        Map<String, String> oldPayReturnMap = redisCache.getCacheObject(WxPayCommon.getWxPayCacheKey(weChatPayVO));
//        if (oldPayReturnMap != null) {
//            return oldPayReturnMap;
//        }
        RSAAutoCertificateConfig config = wxPayConfig.getWxPayConfig();
        // 构建service
        H5Service service = new H5Service.Builder().config(config).build();
        //构建请求
        PrepayRequest request = new PrepayRequest();
        //基础信息
        request.setAppid(wxPayConfig.getPayparams().getAppId());
        request.setMchid(wxPayConfig.getPayparams().getMerchantId());
        request.setDescription(weChatPayVO.getDescription());//商品描述
//        request.setDescription("测试商品");//商品描述
        request.setOutTradeNo(weChatPayVO.getOutTradeNo());//商户系统内部订单号
        request.setNotifyUrl(wxPayConfig.getPayparams().getNotifyUrl());
        //订单金额信息
        Amount amount = new Amount();
        amount.setTotal(1);
        request.setAmount(amount);
        //场景信息
       SceneInfo sceneInfo = new SceneInfo();
//        sceneInfo.setPayerClientIp(weChatPayVO.getSceneInfo().getPayerClientIp());
        sceneInfo.setPayerClientIp("14.23.150.211");
        //H5场景信息
        H5Info h5Info = new H5Info();
//        h5Info.setType(weChatPayVO.getH5Info().getType());
        h5Info.setType("Wap");
        sceneInfo.setH5Info(h5Info);
        request.setSceneInfo(sceneInfo);
        try {
            PrepayResponse response = service.prepay(request);
            log.info("H5下单-结束：{}", JSON.toJSONString(response));
            Map<String, String> payReturnMap = wxPayUtil.getPayReturnMap(response);
//            // 将支付信息存入数据库
//            redisCache.setCacheObject(WxPayCommon.getWxPayCacheKey(weChatPayVO), payReturnMap);   
            return payReturnMap;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ServiceException("H5下单-异常");
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
