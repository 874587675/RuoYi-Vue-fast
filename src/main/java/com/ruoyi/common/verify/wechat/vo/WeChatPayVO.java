package com.ruoyi.common.verify.wechat.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeChatPayVO {
    /**
     * 应用ID
     */
    @JsonIgnore
    private String appId;
    /**
     * 商户号
     */
    @JsonIgnore
    private String mchId;
    /**
     * 订单号
     */
    private String outTradeNo;
    /**
     * 商品描述
     */
    @JsonIgnore
    private String description;
    /**
     * 通知地址
     */
    @JsonIgnore
    private String notifyUrl;
    /**
     * 订单金额信息
     */
    private Amount amount;
    /**
     * 支付者信息（JSAPI需提供）
     */
    private Payer payer;

    /**
     * 场景信息(H5需提供)
     */
    private SceneInfo sceneInfo;

    /**
     * H5场景信息
     */
    private H5Info h5Info;

    @Data
    public class Amount {
        /**
         * 订单总金额，单位为分
         */
        private Integer total;
        /**
         * 货币类型，默认为人民币
         */
        private String currency = "CNY";
    }

    @Data
    public class Payer {
        /**
         * 用户openid
         */
        private String openId;
    }
    
    @Data
    public class SceneInfo {
        /**
         * 用户终端IP,支持IPv4和IPv6两种格式的IP地址。
         */
        private String payerClientIp;
    }

    @Data
    public class H5Info {
        /**
         * 场景类型，使用H5支付的场景：Wap、iOS、Android
         */
        private String type;

    }
    
}

