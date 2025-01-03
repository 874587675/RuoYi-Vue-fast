package com.ruoyi.common.verify.wechat.util;

import com.ruoyi.common.utils.uuid.UUID;
import com.ruoyi.common.verify.config.WxPayConfig;
import com.ruoyi.project.business.domain.Order;
import com.wechat.pay.java.core.Config;
import com.wechat.pay.java.service.payments.jsapi.JsapiService;
import com.wechat.pay.java.service.payments.jsapi.model.Amount;
import com.wechat.pay.java.service.payments.jsapi.model.Payer;
import com.wechat.pay.java.service.payments.jsapi.model.PrepayRequest;
import com.wechat.pay.java.service.payments.jsapi.model.PrepayResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.*;

/**
 * @ClassName:WxPayJsapiService
 * @description:
 * @author: zgc
 **/
@Slf4j
@Component
public class WxPayUtil {
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
            if (value != null && !value.trim().isEmpty() && !"sign".equals(key)) {
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
}
