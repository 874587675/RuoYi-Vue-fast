package com.ruoyi.common.verify.wechat.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author ZhangKeChen
 * @Date 2022/9/29
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeChatCreateOrderVO {
    /**
     * 支付类型
     */
    private String payType;
    /**
     * 支付价格
     */
    private Integer totalPrice;
    /**
     * 订单号
     */
    private String outTradeNo;
    /**
     * 商品描述
     */
    private String description;
    /**
     * 回调地址
     */
    private String notifyUrl;
    /**
     * openid(JSAPI需提供)
     */
    private String openId;

    public WeChatCreateOrderVO(String payType,Integer totalPrice,String outTradeNo,String description,String notifyUrl){
        this.payType = payType;
        this.totalPrice = totalPrice;
        this.outTradeNo = outTradeNo;
        this.description = description;
        this.notifyUrl = notifyUrl;
    }
}
