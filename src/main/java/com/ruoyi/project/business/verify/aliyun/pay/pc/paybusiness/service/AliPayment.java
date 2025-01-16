package com.ruoyi.project.business.verify.aliyun.pay.pc.paybusiness.service;

import com.alipay.api.AlipayApiException;
import com.ruoyi.project.business.verify.aliyun.pay.vo.AliPayPayVO;



/**
 * @program: RuoYi-Vue-fast
 * @ClassName AliPayment
 * @description:
 * @author: zgc
 * @date: 2025-01-15 10:03
 * @Version 1.0
 **/
public interface AliPayment {
    String pay(AliPayPayVO aliPayPayVO) throws AlipayApiException;
    
    
}
