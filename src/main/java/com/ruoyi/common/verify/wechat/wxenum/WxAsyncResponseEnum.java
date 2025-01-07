package com.ruoyi.common.verify.wechat.wxenum;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author ZhangKeChen
 * @Date 2022/9/29
 * @Description
 */
@Getter
@AllArgsConstructor
public enum WxAsyncResponseEnum {
    WX_PAY_ASYNC_RESPONSE_SUCCESS("SUCCESS", "成功"),
    WX_PAY_ASYNC_RESPONSE_FAIT("FAIL", "失败");

    private String code;
    private String message;

}
