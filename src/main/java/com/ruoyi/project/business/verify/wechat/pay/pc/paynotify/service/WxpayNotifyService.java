package com.ruoyi.project.business.verify.wechat.pay.pc.paynotify.service;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.enums.OrderStatus;
import com.ruoyi.common.enums.TradeStatus;
import com.ruoyi.common.utils.random.RandomUtils;
import com.ruoyi.common.verify.wechat.util.WxPayUtil;
import com.ruoyi.common.verify.wechat.vo.WxPayAsyncDecryptVO;
import com.ruoyi.common.verify.wechat.vo.WxPayAsyncVO;
import com.ruoyi.project.business.domain.Order;
import com.ruoyi.project.business.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;

@Service
@Slf4j
public class WxpayNotifyService {
    @Resource
    private WxPayUtil wxPayUtil;
    @Resource
    private OrderService orderService;
    @Resource
    private RandomUtils randomUtils;
    public String wxnotify(HttpServletRequest request) throws IOException {
        // 获取请求体数据
        StringBuilder requestBody = new StringBuilder();
        String line;
        BufferedReader reader = request.getReader();
        while ((line = reader.readLine()) != null) {
            requestBody.append(line);
        }
        String body = requestBody.toString();
        WxPayAsyncVO wxPayAsyncVo = JSONObject.parseObject(body, WxPayAsyncVO.class);
        
        //验签
        boolean result = wxPayUtil.verifySign(request,body);
        if(result){
            // 验签通过，开始解密
            log.info("通知验签成功");
            //解密
            String decryptedString = wxPayUtil.decryptToString(wxPayAsyncVo.getResource().getAssociated_data(), wxPayAsyncVo.getResource().getNonce(), wxPayAsyncVo.getResource().getCiphertext());
            log.info("微信支付回调 - 解密: {}", decryptedString);
            log.info("解密报文：{}", decryptedString);
            //序列化
            WxPayAsyncDecryptVO wxPayAsyncDecryptRequestVo = JSONObject.parseObject(decryptedString, WxPayAsyncDecryptVO.class);
            log.info("将解密报文反序列化：{}", wxPayAsyncDecryptRequestVo);
            
            // 序列化完 拿到数据开始处理回调逻辑
            Order order = Order.builder()
                    .orderNo(randomUtils.generateNumeric(11))
                    .openId(wxPayAsyncDecryptRequestVo.getPayer().getOpenid())
                    .orderNo(wxPayAsyncDecryptRequestVo.getOutTradeNo())
                    .amount(BigDecimal.valueOf(wxPayAsyncDecryptRequestVo.getAmount().getTotal()).divide(BigDecimal.valueOf(100)))
                    .orderStatus(OrderStatus.PAYED.getCode())
                    .orderStatusDesc(OrderStatus.PAYED.getStatus())
                    .tradeStatus(TradeStatus.SUCCESS.getCode())
                    .tradeStatusDesc(TradeStatus.SUCCESS.getStatus())
                    .build();
            // 设置订单编号
            orderService.save(order);
            
            // todo 支付成功后往支付成功表中插入数据
            
            if(order!= null){
                // 你的业务逻辑
                // 如：修改订单状态，发货等
                log.info("微信支付回调 - 订单状态：{}", order.getOrderStatusDesc());
            }else{
                log.error("微信支付回调 - 订单状态：{}", "未找到该订单");
            }
            return "SUCCESS";
        }else {
            return "ERROR";
        }
        
    }
    
}
