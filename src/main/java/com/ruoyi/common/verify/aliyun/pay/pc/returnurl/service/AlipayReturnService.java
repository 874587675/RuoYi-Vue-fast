package com.ruoyi.common.verify.aliyun.pay.pc.returnurl.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class AlipayReturnService {
    
    public void returnUrl(HttpServletRequest request, HttpServletResponse response) {
        String orderNo = request.getParameter("out_trade_no");
        String tradeNo = request.getParameter("trade_no");
        String totalAmount = request.getParameter("total_amount");

        // 处理支付成功后的操作
        // 例如，更新订单状态、发送支付成功邮件等
        System.out.println("订单号：" + orderNo + " 支付成功，支付金额：" + totalAmount + " 支付宝交易号：" + tradeNo);
    }
}
