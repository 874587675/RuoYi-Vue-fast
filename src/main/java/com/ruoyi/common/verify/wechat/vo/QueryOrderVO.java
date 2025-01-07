package com.ruoyi.common.verify.wechat.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class QueryOrderVO {


    @JsonProperty("amount")
    private AmountDTO amount;
    @JsonProperty("appid")
    private String appid;
    @JsonProperty("attach")
    private String attach;
    @JsonProperty("bank_type")
    private String bankType;
    @JsonProperty("mchid")
    private String mchid;
    @JsonProperty("out_trade_no")
    private String outTradeNo;
    @JsonProperty("payer")
    private PayerDTO payer;
    @JsonProperty("promotion_detail")
    private List<?> promotionDetail;
    @JsonProperty("success_time")
    private String successTime;
    @JsonProperty("trade_state")
    private String tradeState;
    @JsonProperty("trade_state_desc")
    private String tradeStateDesc;
    @JsonProperty("trade_type")
    private String tradeType;
    @JsonProperty("transaction_id")
    private String transactionId;

    @NoArgsConstructor
    @Data
    public static class AmountDTO {
        @JsonProperty("currency")
        private String currency;
        @JsonProperty("payer_currency")
        private String payerCurrency;
        @JsonProperty("payer_total")
        private Integer payerTotal;
        @JsonProperty("total")
        private Integer total;
    }

    @NoArgsConstructor
    @Data
    public static class PayerDTO {
        @JsonProperty("openid")
        private String openid;
    }
}
