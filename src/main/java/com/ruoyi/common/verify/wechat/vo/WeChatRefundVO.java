package com.ruoyi.common.verify.wechat.vo;

import lombok.Data;

/**
 * @Author ZhangKeChen
 * @Date 2022/9/28
 * @Description
 */
@Data
public class WeChatRefundVO {
    /**
     * 商家订单号
     */
    private String out_trade_no;
    /**
     * 商户退款单号
     */
    private String out_refund_no;
    /**
     * 订单金额信息
     */
    private WeChatRefundVO.amount amount;

    @Data
    public static class amount {
        /**
         * 退款金额
         */
        private Integer refund;
        /**
         * 原订单金额
         */
        private Integer total;
        private String currency = "CNY";
    }
}
