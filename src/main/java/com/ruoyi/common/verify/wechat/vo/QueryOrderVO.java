package com.ruoyi.common.verify.wechat.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class QueryOrderVO {
    /**
     * 公众账号ID
     */
    @JsonProperty("appid")
    private String appid;
    
    /**
     * 商户号
     */
    @JsonProperty("mchid")
    private String mchid;
    
    /**
     * 商户订单号
     */
    @JsonProperty("out_trade_no")
    private String outTradeNo;

    /**
     * 微信支付订单号
     */
    @JsonProperty("transaction_id")
    private String transactionId;

    /**
     * 交易类型
     * JSAPI：公众号支付、小程序支付
     * NATIVE：Native支付
     * APP：APP支付
     * MICROPAY：付款码支付
     * MWEB：H5支付
     * FACEPAY：刷脸支付
     */
    @JsonProperty("trade_type")
    private String tradeType;
    
    /**
     * 交易状态
     * SUCCESS：支付成功
     * REFUND：转入退款
     * NOTPAY：未支付
     * CLOSED：已关闭
     * REVOKED：已撤销（仅付款码支付会返回）
     * USERPAYING：用户支付中（仅付款码支付会返回）
     * PAYERROR：支付失败（仅付款码支付会返回）
     */
    @JsonProperty("trade_state")
    private String tradeState;

    /**
     * 交易状态描述
     */
    @JsonProperty("trade_state_desc")
    private String tradeStateDesc;

    /**
     * 银行类型
     */
    @JsonProperty("bank_type")
    private String bankType;

    /**
     * 支付完成时间
     */
    @JsonProperty("success_time")
    private String successTime;

    /**
     * 支付者信息
     */
    @JsonProperty("payer")
    private Payer payer;

    /**
     * 订单金额
     */
    @JsonProperty("amount")
    private Amount amount;
    

    @NoArgsConstructor
    @Data
    public static class Amount {
        /**
         * 货币类型 固定返回：CNY，代表人民币。
         */
        @JsonProperty("currency")
        private String currency;
        /**
         * 用户支付币种 订单支付成功后固定返回：CNY，代表人民币。
         */
        @JsonProperty("payer_currency")
        private String payerCurrency;
        /**
         * 订单金额 单位为分
         */
        @JsonProperty("payer_total")
        private Integer payerTotal;
        /**
         * 订单总金额 单位为分
         */
        @JsonProperty("total")
        private Integer total;
    }

    @NoArgsConstructor
    @Data
    public static class Payer {
        /**
         * 用户在商户appid下的唯一标识
         */
        @JsonProperty("openid")
        private String openid;
    }
}
