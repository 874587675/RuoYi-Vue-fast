package com.ruoyi.common.verify.wechat.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 微信创建订单对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeChatCreateOrderVO {
    /**
     * 应用ID
     */
    @JsonIgnore
    private String appId;
    /**
     * 商户号
     */
    @JsonIgnore
    private String mchId;
    /**
     * 订单号
     */
    private String outTradeNo;
    /**
     * 商品描述
     */
    @JsonIgnore
    private String description;
    /**
     * 通知地址
     */
    @JsonIgnore
    private String notifyUrl;
    /**
     * 订单金额信息
     */
    private Amount amount;
    /**
     * 支付者信息（JSAPI需提供）
     */
    private Payer payer;

    @Data
    public class Amount {
        /**
         * 订单总金额，单位为分
         */
        private Integer total;
        /**
         * 货币类型，默认为人民币
         */
        private String currency = "CNY";
    }

    @Data
    public class Payer {
        /**
         * 用户openid
         */
        private String openId;
    }
    
}
