// This file is auto-generated, don't edit it. Thanks.
package com.ruoyi.common.verify.sms.aliyun;

import com.aliyun.auth.credentials.Credential;
import com.aliyun.auth.credentials.provider.StaticCredentialProvider;
import com.aliyun.sdk.service.dysmsapi20170525.AsyncClient;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsResponse;
import com.google.gson.Gson;
import com.ruoyi.common.constant.CacheConstants;
import com.ruoyi.common.utils.random.RandomUtils;
import com.ruoyi.framework.redis.RedisCache;
import darabonba.core.client.ClientOverrideConfiguration;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Configuration
@ConfigurationProperties(prefix = "aliyun")
@Data
public class SendSmsService {
    @Resource
    private RedisCache redisCache;
    
    // AccessKey参数配置
    private AccessKey accesskey;
    // SmsParams参数配置
    private SmsParams smsparams;

    public void SendPhoneCodeToLoginOrRegister(String phone) throws ExecutionException, InterruptedException {
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

        // Configure Credentials authentication information, including ak, secret, token
        // 凭证提供者
        StaticCredentialProvider provider = StaticCredentialProvider.create(Credential.builder()
                // Please ensure that the environment variables ALIBABA_CLOUD_ACCESS_KEY_ID and ALIBABA_CLOUD_ACCESS_KEY_SECRET are set.
                //.accessKeyId(System.getenv("ALIBABA_CLOUD_ACCESS_KEY_ID"))
                //.accessKeySecret(System.getenv("ALIBABA_CLOUD_ACCESS_KEY_SECRET"))
                .accessKeyId(accesskey.getAccessKeyId())
                .accessKeySecret(accesskey.getAccessKeySecret())
                //.securityToken(System.getenv("ALIBABA_CLOUD_SECURITY_TOKEN")) // use STS token
                .build());

        // Configure the Client
        // 客户端配置，创建异步客户端
        AsyncClient client = AsyncClient.builder()
                //.httpClient(httpClient) // Use the configured HttpClient, otherwise use the default HttpClient (Apache HttpClient)
                .credentialsProvider(provider)
                //.serviceConfiguration(Configuration.create()) // Service-level configuration
                // Client-level configuration rewrite, can set Endpoint, Http request parameters, etc.
                .overrideConfiguration(
                        ClientOverrideConfiguration.create()
                                // Endpoint 请参考 https://api.aliyun.com/product/Dysmsapi
                                .setEndpointOverride(smsparams.getEndpoint())
                        //.setConnectTimeout(Duration.ofSeconds(30))
                )
                .build();

        String code = RandomUtils.generateNumeric(6);//生成6位随机验证码

        redisCache.setCacheObject(CacheConstants.CAPTCHA_CODE_KEY, phone + code, 5, TimeUnit.MINUTES);//存入redis中，设置过期时间5分钟

        // Parameter settings for API request
        SendSmsRequest sendSmsRequest = SendSmsRequest.builder()
                .phoneNumbers(phone)
                .signName(smsparams.getSignName())
                .templateCode(smsparams.getTemplateCode())
                .templateParam("{code:" + code + "}")
                // Request-level configuration rewrite, can set Http request parameters, etc.
                // .requestConfiguration(RequestConfiguration.create().setHttpHeaders(new HttpHeaders()))
                .build();
        System.out.println("验证码code为:" + code);
        // Asynchronously get the return value of the API request
        // 使用CompletableFuture异步返回值
        CompletableFuture<SendSmsResponse> response = client.sendSms(sendSmsRequest);
        // Synchronously get the return value of the API request
        SendSmsResponse resp = response.get();
        System.out.println(new Gson().toJson(resp));
        // Asynchronous processing of return values
        /*response.thenAccept(resp -> {
            System.out.println(new Gson().toJson(resp));
        }).exceptionally(throwable -> { // Handling exceptions
            System.out.println(throwable.getMessage());
            return null;
        });*/
        // Finally, close the client
        client.close();
    }
    
    //验证redis中的手机验证码
    public boolean verifyPhoneCode(String phone, String code) {
        String redisCode = redisCache.getCacheObject(CacheConstants.CAPTCHA_CODE_KEY);
        return redisCode!= null && redisCode.equalsIgnoreCase(phone + code);
    }

    @Data
    public static class AccessKey {
        private String AccessKeyId;
        private String AccessKeySecret;
    }

    @Data
    public static class SmsParams {
        private String UserPrincipalName;
        private String Password;
        private String SignName;
        private String Endpoint;
        private String TemplateCode;
    }

}

