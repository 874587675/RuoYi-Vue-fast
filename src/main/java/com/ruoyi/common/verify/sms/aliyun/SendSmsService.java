// This file is auto-generated, don't edit it. Thanks.
package com.ruoyi.common.verify.sms.aliyun;

import com.alibaba.fastjson2.JSON;
import com.aliyun.auth.credentials.Credential;
import com.aliyun.auth.credentials.provider.StaticCredentialProvider;
import com.aliyun.sdk.service.dysmsapi20170525.AsyncClient;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsResponse;
import com.ruoyi.common.constant.CacheConstants;
import com.ruoyi.common.utils.random.RandomUtils;
import com.ruoyi.framework.redis.RedisCache;
import darabonba.core.client.ClientOverrideConfiguration;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Configuration
@ConfigurationProperties(prefix = "aliyun")
@EnableConfigurationProperties
@Data
@Slf4j
public class SendSmsService {
    @Resource
    private RedisCache redisCache;
    
    // SmsParams参数配置
    private SmsParams smsparams;

    public Boolean SendPhoneCodeToLoginOrRegister(String phone) throws ExecutionException, InterruptedException {
        // HttpClient Configuration
        /*HttpClient httpClient = new ApacheAsyncHttpClientBuilder()
                .connectionTimeout(Duration.ofSeconds(10)) // Set the connection timeout time, the default is 10 seconds
                .responseTimeout(Duration.ofSeconds(10)) // Set the response timeout time, the default is 20 seconds
                .maxConnections(128) // Set the connection pool size
                .maxIdleTimeOut(Duration.ofSeconds(50)) // Set the connection pool timeout, the default is 30 seconds
                // Configure the proxy
                .proxy(new ProxyOptions(ProxyOptions.Type.HTTP, new InetSocketAddress("<your-proxy-hostname>", 9001))
                        .setCredentials("<your-proxy-username>", "<your-proxy-password>"))
                // If it is an https connection, you need to configure the certificate, or ignore the certificate(.ignoreSSL(true))
                .x509TrustManagers(new X509TrustManager[]{})
                .keyManagers(new KeyManager[]{})
                .ignoreSSL(false)
                .build();*/
        StaticCredentialProvider provider = StaticCredentialProvider.create(Credential.builder()// 凭证提供者
                .accessKeyId(System.getenv("ALIBABA_CLOUD_ACCESS_KEY_ID"))
                .accessKeySecret(System.getenv("ALIBABA_CLOUD_ACCESS_KEY_SECRET"))
                //.securityToken(System.getenv("ALIBABA_CLOUD_SECURITY_TOKEN")) // 使用 STS token
                .build());
        
        AsyncClient client = AsyncClient.builder()// 客户端配置，创建异步客户端
                .region(smsparams.RegionId)
                //.httpClient(httpClient) // Use the configured HttpClient, otherwise use the default HttpClient (Apache HttpClient)
                .credentialsProvider(provider)
                //.serviceConfiguration(Configuration.create()) // Service-level configuration
                .overrideConfiguration(
                        ClientOverrideConfiguration.create().setEndpointOverride(smsparams.getEndpoint())// Endpoint 请参考 https://api.aliyun.com/product/Dysmsapi
                        //.setConnectTimeout(Duration.ofSeconds(30))
                ).build();

        String code = RandomUtils.generateNumeric(6);//生成6位随机验证码

        redisCache.setCacheObject(CacheConstants.CAPTCHA_CODE_KEY, phone + code, 5, TimeUnit.MINUTES);//存入redis中，设置过期时间5分钟

        
        SendSmsRequest sendSmsRequest = SendSmsRequest.builder()  //参数设置
                .phoneNumbers(phone)
                .signName(smsparams.getSignName())
                .templateCode(smsparams.getTemplateCode())
                .templateParam("{code:" + code + "}")
                // .requestConfiguration(RequestConfiguration.create().setHttpHeaders(new HttpHeaders()))  设置HTTP请求头
                .build();
        log.info("验证码code为:{}" , code);
        CompletableFuture<SendSmsResponse> response = client.sendSms(sendSmsRequest);// 使用CompletableFuture异步返回值
        SendSmsResponse resp = response.get();   // 同步执行
        System.out.println(JSON.toJSON(resp));  
        //异步执行输出
//        response.thenAccept(resp -> {   
//            System.out.println(new Gson().toJson(resp));
//        }).exceptionally(throwable -> { // 处理异常
//            System.out.println(throwable.getMessage());
//            return null;
//        });
        client.close(); // 关闭客户端
        return true;
    }
    
    //验证redis中的手机验证码
    public boolean verifyPhoneCode(String phone, String code) {
        String redisCode = redisCache.getCacheObject(CacheConstants.CAPTCHA_CODE_KEY);
        return redisCode!= null && redisCode.equalsIgnoreCase(phone + code);
    }
    
    @Data
    public static class SmsParams {
        private String UserPrincipalName;
        private String Password;
        private String SignName;
        private String Endpoint;
        private String TemplateCode;
        private String RegionId;
    }

}

