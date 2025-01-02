package com.ruoyi.common.verify.aliyun.pay.pc.paynotify.service;

import com.alipay.api.internal.util.AlipaySignature;
import com.ruoyi.common.verify.config.AlipayConfigs;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Service
public class AlipayNotifyService {
    @Resource
    private AlipayConfigs alipayConfigs;
    // 验证支付通知合法性
    public boolean verifyNotify(HttpServletRequest request) throws Exception {
        // 获取请求参数
        Map<String, String[]> paramMap = request.getParameterMap();

        // 将 Map<String, String[]> 转换为 Map<String, String>
        Map<String, String> params = new HashMap<>();
        for (Map.Entry<String, String[]> entry : paramMap.entrySet()) {
            // 取数组中的第一个值
            String value = (entry.getValue() != null && entry.getValue().length > 0) ? entry.getValue()[0] : null;
            params.put(entry.getKey(), value);
        }
        
        // 支付宝公钥
        boolean signVerified = AlipaySignature.rsaCheckV1(
                params,
                alipayConfigs.getPayparams().getAlipayPublicKey(),
                alipayConfigs.getPayparams().getCharset(),
                alipayConfigs.getPayparams().getSignType());
        if (signVerified) {
            String tradeStatus = request.getParameter("trade_status");
            if ("TRADE_SUCCESS".equals(tradeStatus)) {
                // 支付成功，更新订单状态
                String orderNo = request.getParameter("out_trade_no");
                double totalAmount = Double.parseDouble(request.getParameter("total_amount"));
                // 更新订单信息或执行业务逻辑
                return true;
            } else {
                // 支付失败
                return false;
            }
        } else {
            // 验证失败
            return false;
        }
    }
}
