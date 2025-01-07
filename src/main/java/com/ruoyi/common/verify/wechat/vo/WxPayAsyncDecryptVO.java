package com.ruoyi.common.verify.wechat.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author ZhangKeChen
 * @Date 2022/9/29
 * @Description
 */
@NoArgsConstructor
@Data
public class WxPayAsyncDecryptVO {
    @JsonProperty("transaction_id")
    private String transactionId;
    @JsonProperty("amount")
    private AmountDTO amount;
    @JsonProperty("mchid")
    private String mchid;
    @JsonProperty("trade_state")
    private String tradeState;
    @JsonProperty("bank_type")
    private String bankType;
    @JsonProperty("promotion_detail")
    private List<PromotionDetailDTO> promotionDetail;
    @JsonProperty("success_time")
    private String successTime;
    @JsonProperty("payer")
    private PayerDTO payer;
    @JsonProperty("out_trade_no")
    private String outTradeNo;
    @JsonProperty("appid")
    private String appid;
    @JsonProperty("trade_state_desc")
    private String tradeStateDesc;
    @JsonProperty("trade_type")
    private String tradeType;
    @JsonProperty("attach")
    private String attach;
    @JsonProperty("scene_info")
    private SceneInfoDTO sceneInfo;

    @NoArgsConstructor
    @Data
    public static class AmountDTO {
        @JsonProperty("payer_total")
        private Integer payerTotal;
        @JsonProperty("total")
        private Integer total;
        @JsonProperty("currency")
        private String currency;
        @JsonProperty("payer_currency")
        private String payerCurrency;
    }

    @NoArgsConstructor
    @Data
    public static class PayerDTO {
        @JsonProperty("openid")
        private String openid;
    }

    @NoArgsConstructor
    @Data
    public static class SceneInfoDTO {
        @JsonProperty("device_id")
        private String deviceId;
    }

    @NoArgsConstructor
    @Data
    public static class PromotionDetailDTO {
        @JsonProperty("amount")
        private Integer amount;
        @JsonProperty("wechatpay_contribute")
        private Integer wechatpayContribute;
        @JsonProperty("coupon_id")
        private String couponId;
        @JsonProperty("scope")
        private String scope;
        @JsonProperty("merchant_contribute")
        private Integer merchantContribute;
        @JsonProperty("name")
        private String name;
        @JsonProperty("other_contribute")
        private Integer otherContribute;
        @JsonProperty("currency")
        private String currency;
        @JsonProperty("stock_id")
        private String stockId;
        @JsonProperty("goods_detail")
        private List<GoodsDetailDTO> goodsDetail;

        @NoArgsConstructor
        @Data
        public static class GoodsDetailDTO {
            @JsonProperty("goods_remark")
            private String goodsRemark;
            @JsonProperty("quantity")
            private Integer quantity;
            @JsonProperty("discount_amount")
            private Integer discountAmount;
            @JsonProperty("goods_id")
            private String goodsId;
            @JsonProperty("unit_price")
            private Integer unitPrice;
        }
    }
}
