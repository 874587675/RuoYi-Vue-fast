package com.ruoyi.common.verify.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program:
 * @ClassName:
 * @description:
 * @author: zgc
 * @date:
 * @Version 1.0
 **/
@Configuration
@ConfigurationProperties(prefix = "zfb")
@Data
public class AlipayConfig {
    private PayParams payparams;
    
    @Bean
    public com.alipay.api.AlipayConfig getAlipayConfig() {
        com.alipay.api.AlipayConfig alipayConfig = new com.alipay.api.AlipayConfig(); //支付宝配置
        alipayConfig.setAppId(payparams.getAppId());    //应用ID
        alipayConfig.setPrivateKey(payparams.getMerchantPrivateKey());  //商户私钥
        alipayConfig.setAlipayPublicKey(payparams.getAlipayPublicKey());
        alipayConfig.setServerUrl(payparams.getGatewayUrl());   //支付宝网关
        alipayConfig.setSignType(payparams.getSignType());  //签名方式
        alipayConfig.setCharset(payparams.getCharset());    //字符编码格式
        alipayConfig.setFormat(payparams.getFormat());  //格式
        return alipayConfig;
    }
    
    @Data
    public static class PayParams {
        private String AppId;
        private String MerchantPrivateKey;
        private String AlipayPublicKey;
        private String GatewayUrl;
        private String NotifyUrl;
        private String ReturnUrl;
        private String SignType;
        private String Charset;
        private String Format;
        private String Version;
        private String ProductCode;
    }

}
