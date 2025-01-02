package com.ruoyi.project.business.test;

import com.wechat.pay.java.core.Config;
import com.wechat.pay.java.core.RSAAutoCertificateConfig;


import com.wechat.pay.java.service.payments.nativepay.NativePayService;
import com.wechat.pay.java.service.payments.nativepay.model.Amount;
import com.wechat.pay.java.service.payments.nativepay.model.PrepayRequest;
import com.wechat.pay.java.service.payments.nativepay.model.PrepayResponse;
import com.wechat.pay.java.service.wexinpayscoreparking.model.Payer;

/** JSAPI 下单为例 */
public class QuickStart {

  /** 商户号 */
  public static String merchantId = "1606444287";

  /** 商户API私钥路径 */
  public static String privateKeyPath = "cert_wxpay/apiclient_key.pem";

  /** 商户证书序列号 */
  public static String merchantSerialNumber = "3581E71E4D77A1EE57D50E8FEB20F3B977C3C386";

  /** 商户APIV3密钥 */
  public static String apiV3Key = "lqozpTpcLhz4NiMfL2o99zFdsI5IkEDv";

  public static void main(String[] args) {
    // 使用自动更新平台证书的RSA配置
    // 一个商户号只能初始化一个配置，否则会因为重复的下载任务报错
    Config config =
            new RSAAutoCertificateConfig.Builder()
                    .merchantId(merchantId)
                    .privateKeyFromPath(privateKeyPath)
                    .merchantSerialNumber(merchantSerialNumber)
                    .apiV3Key(apiV3Key)
                    .build();
    NativePayService service = new NativePayService.Builder().config(config).build();
    // request.setXxx(val)设置所需参数，具体参数可见Request定义
    PrepayRequest request = new PrepayRequest();
    
    Amount amount = new Amount();
    amount.setTotal(100);
    request.setAmount(amount);
    request.setAppid("wxff0cf54e2af1fa40");
    request.setMchid("1606444287");
    request.setDescription("测试商品标题");
    request.setNotifyUrl("https://notify_url");
    request.setOutTradeNo("out_trade_no_001");

    PrepayResponse prepay = service.prepay(request);

    System.out.println(prepay.getCodeUrl());
  }
  
}
