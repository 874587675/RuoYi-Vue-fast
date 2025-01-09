package com.ruoyi.project.business.verify.wechat.pay.pc.paybusiness.service.impl;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.enums.OrderStatus;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.random.RandomUtils;
import com.ruoyi.common.utils.uuid.UUID;
import com.ruoyi.common.verify.config.WxPayConfig;
import com.ruoyi.common.verify.wechat.util.WxPayUtil;
import com.ruoyi.common.verify.wechat.vo.QueryOrderVO;
import com.ruoyi.common.verify.wechat.vo.WeChatCreateOrderVO;
import com.ruoyi.common.verify.wechat.vo.WeChatPayVO;
import com.ruoyi.framework.redis.RedisCache;
import com.ruoyi.project.business.domain.Order;
import com.ruoyi.project.business.mapper.OrderMapper;
import com.ruoyi.project.business.verify.wechat.pay.pc.paybusiness.common.WxPayCommon;
import com.ruoyi.project.business.verify.wechat.pay.pc.paybusiness.service.WxPayment;
import com.wechat.pay.java.core.Config;
import com.wechat.pay.java.service.payments.jsapi.JsapiService;
import com.wechat.pay.java.service.payments.jsapi.JsapiServiceExtension;
import com.wechat.pay.java.service.payments.jsapi.model.*;
import com.wechat.pay.java.service.payments.model.Transaction;
import lombok.extern.slf4j.Slf4j;
import okhttp3.HttpUrl;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static com.ruoyi.project.business.verify.wechat.pay.pc.paybusiness.common.WxPayCommon.QUERY_OUT_TRADE_NO;
import static com.ruoyi.project.business.verify.wechat.pay.pc.paybusiness.common.WxPayCommon.QUERY_TRANSACTIONID;

/**
 * @ClassName: WxPayJsapiService
 * @description: 微信支付jsapi支付
 * @author: zgc
 **/
@Service
@Slf4j
public class WxPayJsapiService implements WxPayment {
    @Resource
    private WxPayConfig wxPayConfig;
    @Resource
    private WxPayUtil wxPayUtil;
    @Resource
    private RedisCache redisCache;

    /**
     * 这是自己写的支付，需要自己签名
     * 另外一种写法是调用微信写好的
     * @param weChatPayVO
     * @return
     * @throws Exception
     */
    @Override
    public Map<String, String> wxPay(WeChatPayVO weChatPayVO) throws Exception {
        /*
        第一种自己写的
         */
//        Map<String, String> oldPayReturnMap = redisCache.getCacheObject(WxPayCommon.getWxPayCacheKey(weChatPayVO));
//        if (oldPayReturnMap != null) {
//            return oldPayReturnMap;
//        }
//        Config config = wxPayConfig.getWxPayConfig();
//        JsapiService service = new JsapiService.Builder().config(config).build();
//        PrepayRequest request = new PrepayRequest();
//        Amount amount = new Amount();
//        amount.setTotal(1);
//        request.setAmount(amount);
//        request.setAppid(wxPayConfig.getPayparams().getAppId());
//        request.setMchid(wxPayConfig.getPayparams().getMerchantId());
//        request.setDescription(weChatPayVO.getDescription());     //商品信息描述
//        request.setNotifyUrl(wxPayConfig.getPayparams().getNotifyUrl());    // 设置回调地址
//        request.setOutTradeNo(weChatPayVO.getOutTradeNo());
//        Payer payer = new Payer();
//
//        String openId = "ou6YH41g7ceN1XECAoVaCgKeYAco";
//        weChatPayVO.getPayer().setOpenId(openId); //后面删掉就行
//
//        payer.setOpenid(weChatPayVO.getPayer().getOpenId());
//        request.setPayer(payer);
//        PrepayResponse prepayResponse = service.prepay(request);
//        // 获取返回的prepay_id
//        String prepayId = prepayResponse.getPrepayId();
//        //生成签名 根据微信返回拼接参数返回
//        Map<String, String> payReturnMap = wxPayUtil.getPayReturnMap(prepayId);
//        // 将支付信息存入数据库
//        redisCache.setCacheObject(WxPayCommon.getWxPayCacheKey(weChatPayVO), payReturnMap);
//        return payReturnMap;

        /*
        第二种 微信官方提供的
         */
        Map<String, String> oldPayReturnMap = redisCache.getCacheObject(WxPayCommon.getWxPayCacheKey(weChatPayVO));
        if (oldPayReturnMap != null) {
            return oldPayReturnMap;
        }
        Config config = wxPayConfig.getWxPayConfig();
        JsapiServiceExtension service = new JsapiServiceExtension.Builder().config(config).build();
        //构建请求
        PrepayRequest request = new PrepayRequest();
        //基础信息
        request.setAppid(wxPayConfig.getPayparams().getAppId());
        request.setMchid(wxPayConfig.getPayparams().getMerchantId());
        request.setDescription(weChatPayVO.getDescription());     //商品信息描述
        request.setNotifyUrl(wxPayConfig.getPayparams().getNotifyUrl());    // 设置回调地址
        request.setOutTradeNo(weChatPayVO.getOutTradeNo());
//        request.setAttach(attach);//附加数据
        //订单金额信息
        Amount amount = new Amount();
        amount.setTotal(1);
        request.setAmount(amount);
        
        //用户在直连商户appid下的唯一标识。 下单前需获取到用户的Openid
        Payer payer = new Payer();
        String openId = "ou6YH41g7ceN1XECAoVaCgKeYAco";
        weChatPayVO.getPayer().setOpenId(openId); //后面删掉就行
        payer.setOpenid(weChatPayVO.getPayer().getOpenId());
        request.setPayer(payer);
        try {
            PrepayWithRequestPaymentResponse response = service.prepayWithRequestPayment(request);
            log.info("JSAPI下单-结果：{}", JSON.toJSONString(response));
            Map<String, String> payReturnMap = wxPayUtil.getPayReturnMap(response);
            // 将支付信息存入数据库
            redisCache.setCacheObject(WxPayCommon.getWxPayCacheKey(weChatPayVO), payReturnMap);
            return payReturnMap;
        } catch (Exception ex) {
            throw new ServiceException("JSAPI下单-失败");
        }
    }

    @Override
    public Transaction queryOrderByTransactionId(String transactionId) throws Exception {
        Config config = wxPayConfig.getWxPayConfig();
        // 构建service
        JsapiServiceExtension service = new JsapiServiceExtension.Builder().config(config).build();
        QueryOrderByIdRequest request = new QueryOrderByIdRequest();
        request.setMchid(wxPayConfig.getPayparams().getMerchantId());
        request.setTransactionId(transactionId);
        try {
            Transaction transaction = service.queryOrderById(request);
            log.info("查询订单-微信支付微信订单号查询-结束：{}",JSON.toJSONString(transaction));
            return transaction;
        } catch (Exception ex) {
            throw new ServiceException("查询订单-微信支付微信订单号查询-失败");
        }
    }

    @Override
    public Transaction queryOrderByOutTradeNo(String outTradeNo) throws Exception {
        Config config = wxPayConfig.getWxPayConfig();
        // 构建service
        JsapiServiceExtension service = new JsapiServiceExtension.Builder().config(config).build();
        QueryOrderByOutTradeNoRequest request = new QueryOrderByOutTradeNoRequest();
        request.setMchid(wxPayConfig.getPayparams().getMerchantId());
        request.setOutTradeNo(outTradeNo);
        try {
            Transaction transaction = service.queryOrderByOutTradeNo(request);
            log.info("查询订单-微信支付商户订单号查询-结束：{}",JSON.toJSONString(transaction));
            return transaction;
        } catch (Exception ex) {
            throw new ServiceException("查询订单-微信商户支付订单号查询-失败");
        }
    }
}
