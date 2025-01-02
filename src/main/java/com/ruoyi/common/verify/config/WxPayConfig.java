package com.ruoyi.common.verify.config;

import com.wechat.pay.java.core.Config;
import com.wechat.pay.java.core.RSAPublicKeyConfig;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName:WxPayConfig
 * @description:
 * @author: zgc
 **/
@Configuration
@ConfigurationProperties(prefix = "wxpay")
@Data
public class WxPayConfig {
    private PayParams payparams;
    
    @Bean
    public Config getWxPayConfig() {

        return new RSAPublicKeyConfig.Builder()
                //商户号
                .merchantId(payparams.getMerchantId())
                //商户API私钥路径
                .privateKeyFromPath(payparams.getPrivateKeyPath())
                //商户证书序列号
                .merchantSerialNumber(payparams.getMerchantSerialNumber())
                //商户APIV3密钥
                .apiV3Key(payparams.getApiV3Key())
                .build();
    }
    
    @Data
    public static class PayParams {
        private String MerchantId;
        private String AppId;
        private String PrivateKeyPath;
        private String MerchantSerialNumber;
        private String ApiV3Key;
        private String NotifyUrl;
        private String ReturnUrl;
    }
}
