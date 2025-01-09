package com.ruoyi.project.business.verify.wechat.pay.pc.paybusiness.service;


import com.ruoyi.common.verify.wechat.vo.WeChatPayVO;
import com.wechat.pay.java.service.payments.model.Transaction;
import com.wechat.pay.java.service.refund.model.Refund;
import com.wechat.pay.java.service.refund.model.RefundNotification;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface WxPayment {
    
    Map<String,String> wxPay(WeChatPayVO weChatPayVO) throws Exception; // 发起支付

    Transaction queryOrderByTransactionId(String transactionId) throws Exception; //根据微信支付订单号查询

    Transaction queryOrderByOutTradeNo(String outTradeNo) throws Exception; //根据微信支付订单号查询

    Boolean closeByOutTradeNo(String outTradeNo);

    Boolean refundsByOutTradeNo(String outTradeNo,String outRefundNo,Integer total, Integer refund);

    Boolean refundsByTransactionId(String transactionId,String outRefundNo,Integer total, Integer refund);

    Refund refundsQueryByOutRefundNo(String outRefundNo);

    Transaction notify(HttpServletRequest request);

    RefundNotification refundNotify(HttpServletRequest request);
}
