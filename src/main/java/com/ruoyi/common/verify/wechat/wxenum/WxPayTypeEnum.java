package com.ruoyi.common.verify.wechat.wxenum;


import com.ruoyi.common.exception.ServiceException;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * @Author ZhangKeChen
 * @Date 2022/9/29
 * @Description
 */
@Getter
@AllArgsConstructor
public enum WxPayTypeEnum {
    /**
     * APP支付
     */
    APP_TYPE("APP", "https://api.mch.weixin.qq.com/v3/pay/transactions/app"),
    /**
     * JSAPI支付
     */
    JSAPI_TYPE("JSAPI", "https://api.mch.weixin.qq.com/v3/pay/transactions/jsapi"),
    /**
     * H5支付
     */
    H5_TYPE("H5", "https://api.mch.weixin.qq.com/v3/pay/transactions/h5"),
    /**
     * NATIVE支付
     */
    NATIVE_TYPE("NATIVE", "https://api.mch.weixin.qq.com/v3/pay/transactions/native");
    private String payType;
    private String payUrl;

    /**
     * 通过枚举的key，获取value
     * @param key
     * @return
     */
    public static String getNameByKey(String key) {
        WxPayTypeEnum[] payArray = values();
        for (WxPayTypeEnum channelEnum : payArray) {
            if (channelEnum.getPayType().equals(key)) {
                return channelEnum.getPayUrl();
            }
        }
        throw new ServiceException("支付类型错误");
    }


}
