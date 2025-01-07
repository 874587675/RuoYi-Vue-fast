package com.ruoyi.common.verify.wechat.vo;

import lombok.Data;

/**
 * @Author ZhangKeChen
 * @Date 2022/9/29
 * @Description
 */
@Data
public class WeChatPayReturnVO {
    /**
     * JSAPI、APP支付返回的预支付交易会话标识
     */
    private String prepay_id;
    /**
     * H5支付返回的支付跳转链接
     */
    private String h5_url;
    /**
     * Native返回的二维码链接
     */
    private String code_url;

}
