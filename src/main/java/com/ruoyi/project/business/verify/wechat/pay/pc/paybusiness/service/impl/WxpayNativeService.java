package com.ruoyi.project.business.verify.wechat.pay.pc.paybusiness.service.impl;

import com.ruoyi.common.utils.random.RandomUtils;
import com.ruoyi.common.verify.config.WxPayConfig;
import com.ruoyi.common.verify.wechat.util.WxPayUtil;
import com.ruoyi.project.business.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @ClassName: WxpayNativeService
 * @description: 微信支付native方式
 * @author: zgc
 **/
@Service
@Slf4j
public class WxpayNativeService {

    @Resource
    private WxPayConfig wxPayConfig;
    @Resource
    private WxPayUtil wxPayUtil;
    @Resource
    private RandomUtils randomUtils;
    
    public Map<String, String> toWxPayByNative(Order order) {
        return null;
    }
}
