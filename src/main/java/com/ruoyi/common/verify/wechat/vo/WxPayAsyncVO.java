package com.ruoyi.common.verify.wechat.vo;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class WxPayAsyncVO {

    /**
     * 通知的唯一ID
     * 示例值：EV-2018022511223320873
     */
    private String id;

    /**
     * 通知创建的时间
     * 示例值：2015-05-20T13:29:35+08:00
     */
    private String create_time;

    /**
     * 通知的类型
     * 示例值：REFUND.SUCCESS
     */
    private String event_type;

    /**
     * 通知的资源数据类型，支付成功通知为encrypt-resource
     * 示例值：encrypt-resource
     */
    private String resource_type;

    /**
     * 回调摘要
     * 示例值：支付成功
     */
    private String summary;

    /**
     * 通知资源数据
     */
    private Resource resource;
    @Data
    public class  Resource {
        private String  original_type;
        private String  algorithm;
        private String  ciphertext;
        private String  associated_data;
        private String  nonce;
    }
}

