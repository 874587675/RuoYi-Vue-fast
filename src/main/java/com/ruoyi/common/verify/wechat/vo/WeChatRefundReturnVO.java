package com.ruoyi.common.verify.wechat.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Description
 */
@NoArgsConstructor
@Data
public class WeChatRefundReturnVO {


    @JsonProperty("refund_id")
    private String refundId;
    @JsonProperty("out_refund_no")
    private String outRefundNo;
    @JsonProperty("transaction_id")
    private String transactionId;
    @JsonProperty("out_trade_no")
    private String outTradeNo;
    @JsonProperty("channel")
    private String channel;
    @JsonProperty("user_received_account")
    private String userReceivedAccount;
    @JsonProperty("success_time")
    private String successTime;
    @JsonProperty("create_time")
    private String createTime;
    @JsonProperty("status")
    private String status;
    @JsonProperty("funds_account")
    private String fundsAccount;
    @JsonProperty("amount")
    private AmountDTO amount;
    @JsonProperty("promotion_detail")
    private List<PromotionDetailDTO> promotionDetail;

    @NoArgsConstructor
    @Data
    public static class AmountDTO {
        @JsonProperty("total")
        private Integer total;
        @JsonProperty("refund")
        private Integer refund;
        @JsonProperty("from")
        private List<FromDTO> from;
        @JsonProperty("payer_total")
        private Integer payerTotal;
        @JsonProperty("payer_refund")
        private Integer payerRefund;
        @JsonProperty("settlement_refund")
        private Integer settlementRefund;
        @JsonProperty("settlement_total")
        private Integer settlementTotal;
        @JsonProperty("discount_refund")
        private Integer discountRefund;
        @JsonProperty("currency")
        private String currency;

        @NoArgsConstructor
        @Data
        public static class FromDTO {
            @JsonProperty("account")
            private String account;
            @JsonProperty("amount")
            private Integer amount;
        }
    }

    @NoArgsConstructor
    @Data
    public static class PromotionDetailDTO {
        @JsonProperty("promotion_id")
        private String promotionId;
        @JsonProperty("scope")
        private String scope;
        @JsonProperty("type")
        private String type;
        @JsonProperty("amount")
        private Integer amount;
        @JsonProperty("refund_amount")
        private Integer refundAmount;
        @JsonProperty("goods_detail")
        private List<GoodsDetailDTO> goodsDetail;

        @NoArgsConstructor
        @Data
        public static class GoodsDetailDTO {
            @JsonProperty("merchant_goods_id")
            private String merchantGoodsId;
            @JsonProperty("wechatpay_goods_id")
            private String wechatpayGoodsId;
            @JsonProperty("goods_name")
            private String goodsName;
            @JsonProperty("unit_price")
            private Integer unitPrice;
            @JsonProperty("refund_amount")
            private Integer refundAmount;
            @JsonProperty("refund_quantity")
            private Integer refundQuantity;
        }
    }
}
