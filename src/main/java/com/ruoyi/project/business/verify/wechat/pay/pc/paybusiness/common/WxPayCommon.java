package com.ruoyi.project.business.verify.wechat.pay.pc.paybusiness.common;

import com.ruoyi.common.verify.wechat.vo.WeChatPayVO;

/**
 * @ClassName:WxPayCommon
 * @description:
 * @author: zgc
 **/
public class WxPayCommon {

    public static final String QUERY_TRANSACTIONID = "https://api.mch.weixin.qq.com/v3/pay/transactions/id/";   //微信订单号查询

    public static final String QUERY_OUT_TRADE_NO = "https://api.mch.weixin.qq.com/v3/pay/transactions/out-trade-no/";   //商户订单号查询
    
    public static final String SCHEMA = "WECHATPAY2-SHA256-RSA2048";
    
    public static final String WECHATPAY_SERIAL = "Wechatpay-Serial";
    public static final String WECHATPAY_NONCE = "Wechatpay-Nonce";
    public static final String WECHATPAY_SIGNATURE = "Wechatpay-Signature";
    public static final String WECHATPAY_TIMESTAMP = "Wechatpay-Timestamp";
    public static final String WECHATPAY_SIGN_TYPE = "Wechatpay-Signature-Type";
    
    public static String getWxPayCacheKey(WeChatPayVO weChatPayVO) {
        return weChatPayVO.getPayer().getOpenId() + "/" + weChatPayVO.getOutTradeNo();
    }
}
