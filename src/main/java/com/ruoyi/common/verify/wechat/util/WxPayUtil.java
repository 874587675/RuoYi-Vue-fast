package com.ruoyi.common.verify.wechat.util;

import com.ruoyi.common.verify.config.WxPayConfig;
import com.wechat.pay.contrib.apache.httpclient.util.AesUtil;
import com.wechat.pay.java.service.payments.jsapi.model.PrepayWithRequestPaymentResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Slf4j
@Component
public class WxPayUtil {
    @Resource
    private WxPayConfig wxPayConfig;
    
    /**
     * 移动端支付请求签名
     * @param payParams
     * @param privateKey
     * @return
     * @throws Exception
     */
    public String generateSignature(Map<String, String> payParams, String privateKey) throws Exception {
        // 1. 排序参数
        Map<String, String> sortedParams = new TreeMap<>(payParams);
        StringBuilder stringBuilder = new StringBuilder();

        Set<Map.Entry<String, String>> entrySet = sortedParams.entrySet();

        for (Map.Entry<String, String> entry : entrySet) {
            String key = entry.getKey();
            String value = entry.getValue();

            // 排除空值与签名字段
            if (value != null && !value.trim().isEmpty() && !"signType".equals(key)) {
                stringBuilder.append(key).append("=").append(value).append("&");
            }
        }
        // 去掉最后的“&”
        String preSign = stringBuilder.substring(0, stringBuilder.length() - 1);

        // 2. 使用私钥进行签名
        Signature signature = Signature.getInstance("SHA256withRSA");
        log.info("privateKey:{}", privateKey);
        signature.initSign(getPrivateKey(privateKey));
        signature.update(preSign.getBytes(StandardCharsets.UTF_8));

        // 3. 返回生成的签名（Base64编码）
        byte[] signedBytes = signature.sign();
        return new String(java.util.Base64.getEncoder().encode(signedBytes), StandardCharsets.UTF_8);
    }
    
    /**
     * 移动端验签解密
     * @param request
     * @param body
     * @return
     * @throws Exception
     */
    public boolean verifySign(HttpServletRequest request, String body) {
        boolean verify = false;
        try {
            //微信支付官方文档
            //第一步
            //HTTP 头 Wechatpay-Timestamp 中的应答时间戳
            //HTTP 头 Wechatpay-Nonce 中的应答随机串
            //应答报文主体（Response Body），请使用原始报文主体执行验签。如果您使用了某个框架，要确保它不会篡改报文主体。对报文主体的任何篡改都会导致验证失败。
            
            String wechatPayTimestamp = request.getHeader("Wechatpay-Timestamp");
            String wechatPayNonce = request.getHeader("Wechatpay-Nonce");
            String wechatPaySerial = request.getHeader("Wechatpay-Serial"); //这是用户获取平台证书，但是这里我们使用微信支付公钥
            //第二步
            //按照以下规则构造应答的验签名串。签名串共有三行，行尾以\n 结束，包括最后一行。
            //  应答时间戳\n
            //  应答随机串\n
            //  应答报文主体\n
            String signStr = Stream.of(wechatPayTimestamp, wechatPayNonce, body)
                    .collect(Collectors.joining("\n", "", "\n"));
            //第三步
            //微信支付的应答签名通过 HTTP 头 Wechatpay-Signature 传递
            String wechatPaySignature = request.getHeader("Wechatpay-Signature");
            
            //第四步
            //使用微信支付公钥对验签名串和签名进行 SHA256 with RSA 签名验证。
            //获取公钥证书
            PublicKey publicKey = loadPublicKey(wxPayConfig.getPayparams().getPublicCertPath());
            if (publicKey != null) {
                // 第三步：使用 SHA256withRSA 验证签名
                Signature signature = Signature.getInstance("SHA256withRSA");
                signature.initVerify(publicKey);
                signature.update(signStr.getBytes(StandardCharsets.UTF_8));

                // 对比请求中的签名是否匹配
                verify = signature.verify(Base64.getDecoder().decode(wechatPaySignature));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return verify;
    }
    
    /**
     * 加载本地公钥证书。
     *
     * @return 公钥对象
     */
//    private PublicKey loadPublicKey(String certificatePath) throws Exception {
//        try (FileInputStream fis = new FileInputStream(certificatePath)) {
//            // 创建一个证书工厂
//            CertificateFactory cf = CertificateFactory.getInstance("X.509");
//            X509Certificate cert = (X509Certificate) cf.generateCertificate(fis);
//
//            // 获取公钥
//            return cert.getPublicKey();
//        } catch (IOException e) {
//            throw new Exception("加载公钥失败", e);
//        }
//    }

    public PublicKey loadPublicKey(String fileName) throws Exception {
        try {
            // 读取公钥 PEM 文件
            ClassPathResource resource = new ClassPathResource(fileName);
            String content = IOUtils.toString(resource.getInputStream(), StandardCharsets.UTF_8);

            // 去除标识符
            String publicKey = content.replace("-----BEGIN CERTIFICATE-----", "")
                    .replace("-----END CERTIFICATE-----", "")
                    .replaceAll("\\s+", "");

            // Base64 解码
            byte[] decodedKey = Base64.getDecoder().decode(publicKey);

            // 使用 X.509 公钥规范
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decodedKey);
            return keyFactory.generatePublic(keySpec);
        } catch (Exception e) {
            throw new Exception("加载公钥失败", e);
        }
    }


    /**
     * 获取私钥。
     *
     * @return 私钥对象
     */
    public PrivateKey getPrivateKey(String fileName) {
        log.info("weChatPayPrivateKeyPath:{}", fileName);
        try {
            ClassPathResource resource = new ClassPathResource(fileName);
            String content = IOUtils.toString(resource.getInputStream(), StandardCharsets.UTF_8);
            String privateKey = content.replace("-----BEGIN PRIVATE KEY-----", "")
                    .replace("-----END PRIVATE KEY-----", "")
                    .replaceAll("\\s+", "");
            KeyFactory kf = KeyFactory.getInstance("RSA");
            PrivateKey key = kf.generatePrivate(new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey)));
            return key;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("当前Java环境不支持RSA", e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException("无效的密钥格式");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 证书和回调报文解密
     *
     * @param associatedData response.body.data[i].encrypt_certificate.associated_data 附加数据包（可能为空）
     * @param nonce          response.body.data[i].encrypt_certificate.nonce 加密使用的随机串初始化向量
     * @param ciphertext     response.body.data[i].encrypt_certificate.ciphertext Base64编码后的密文
     * @return the string
     * @throws GeneralSecurityException the general security exception
     */
    public String decryptToString(String associatedData, String nonce, String ciphertext) {
        String cert = null;
        try {
            //简化
            AesUtil aesUtil = new AesUtil(wxPayConfig.getPayparams().getApiV3Key().getBytes(StandardCharsets.UTF_8));
            cert = aesUtil.decryptToString(associatedData.getBytes(StandardCharsets.UTF_8), nonce.getBytes(StandardCharsets.UTF_8), ciphertext);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        return cert;
    }
    
    public  Map<String, String> getPayReturnMap(Object response) throws Exception {
        // 构建JSAPI支付参数
        if (response instanceof com.wechat.pay.java.service.payments.jsapi.model.PrepayWithRequestPaymentResponse){
            com.wechat.pay.java.service.payments.jsapi.model.PrepayWithRequestPaymentResponse jsapiResponse =
                    (com.wechat.pay.java.service.payments.jsapi.model.PrepayWithRequestPaymentResponse) response;
            Map<String, String> payParams = new TreeMap<>();
            payParams.put("appId", ((PrepayWithRequestPaymentResponse) response).getAppId());
            payParams.put("timeStamp", jsapiResponse.getTimeStamp());
            payParams.put("nonceStr", jsapiResponse.getNonceStr());
            payParams.put("package", "prepay_id=" + jsapiResponse.getPackageVal());
            payParams.put("signType", jsapiResponse.getSignType());
            payParams.put("paySign", jsapiResponse.getPaySign());            //生成签名
            return payParams;           // 返回支付参数
        }
        //构建APP支付参数
        else if (response instanceof com.wechat.pay.java.service.payments.app.model.PrepayWithRequestPaymentResponse) {
            com.wechat.pay.java.service.payments.app.model.PrepayWithRequestPaymentResponse appResponse =
                    (com.wechat.pay.java.service.payments.app.model.PrepayWithRequestPaymentResponse) response;
            Map<String, String> payParams = new TreeMap<>();
            payParams.put("appId", appResponse.getAppid());
            payParams.put("partnerId",appResponse.getPartnerId());
            payParams.put("prepayId", appResponse.getPrepayId());
            payParams.put("timeStamp", appResponse.getTimestamp());
            payParams.put("nonceStr", appResponse.getNonceStr());
            payParams.put("packageValue", appResponse.getPackageVal());
            payParams.put("sign", appResponse.getSign());
            return payParams;           // 返回支付参数
        }
        else if (response instanceof com.wechat.pay.java.service.payments.h5.model.PrepayResponse){
            com.wechat.pay.java.service.payments.h5.model.PrepayResponse h5Response =
                    (com.wechat.pay.java.service.payments.h5.model.PrepayResponse) response;
            Map<String, String> payParams = new TreeMap<>();
            payParams.put("h5_url", h5Response.getH5Url());
            return payParams;           // 返回支付参数
        }
        else if (response instanceof com.wechat.pay.java.service.payments.nativepay.model.PrepayResponse){
            com.wechat.pay.java.service.payments.nativepay.model.PrepayResponse nativeResponse =
                    (com.wechat.pay.java.service.payments.nativepay.model.PrepayResponse) response;
            Map<String, String> payParams = new TreeMap<>();
            payParams.put("code_url", nativeResponse.getCodeUrl());
            return payParams;           // 返回支付参数
        }
        
        return null;
    }
    
}
