package com.ruoyi.common.verify.wechat.vo;

import lombok.Data;

@Data
public class WeChatPayVO {
    /**
     * 应用ID
     */
    private String appid;
    /**
     * 商户号
     */
    private String mchid;
    /**
     * 订单号
     */
    private String out_trade_no;
    /**
     * 商品描述
     */
    private String description;
    /**
     * 通知地址
     */
    private String notify_url;
    /**
     * 订单金额信息
     */
    private Amount amount;
    /**
     * 支付者信息（JSAPI需提供）
     */
    private Payer payer;

    @Data
    class Amount {
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
    class Payer {
        /**
         * 用户openid
         */
        private String openid;

    }

    public WeChatPayVO(Integer total, String openid) {
        Amount amount = new Amount();
        amount.setTotal(total);
        this.amount = amount;

        Payer payer = new Payer();
        payer.setOpenid(openid);
        this.payer = payer;
    }
}

