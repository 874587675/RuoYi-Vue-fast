package com.ruoyi.project.business.verify.wechat.utils;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ResultModel<T> {

    private Boolean isSuccess = false;
    /**
     * 错误码
     */
    private String code = "";
    /**
     * 描述
     */
    private String message = "";

    /**
     * 数据
     */
    private T data;
}
