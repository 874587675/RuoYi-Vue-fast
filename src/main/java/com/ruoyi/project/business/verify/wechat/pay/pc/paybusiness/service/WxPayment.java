package com.ruoyi.project.business.verify.wechat.pay.pc.paybusiness.service;


import com.ruoyi.common.verify.wechat.vo.QueryOrderVO;
import com.ruoyi.common.verify.wechat.vo.WeChatCreateOrderVO;
import com.ruoyi.common.verify.wechat.vo.WeChatPayVO;
import com.ruoyi.project.business.domain.Order;
import com.wechat.pay.java.service.payments.model.Transaction;

import java.util.Map;

public interface WxPayment {
    
    Map<String,String> wxPay(WeChatPayVO weChatPayVO) throws Exception; // 发起支付

    Transaction queryOrderByTransactionId(String transactionId) throws Exception; //根据微信支付订单号查询

    Transaction queryOrderByOutTradeNo(String outTradeNo) throws Exception; //根据微信支付订单号查询
    
    
}
