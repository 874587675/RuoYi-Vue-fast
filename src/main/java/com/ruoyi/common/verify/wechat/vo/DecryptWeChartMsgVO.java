package com.ruoyi.common.verify.wechat.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @Description
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DecryptWeChartMsgVO {
    private String openId;
    private String tel;
    private String accessToken;
    private UserDetailsH5VO userDetails;
}
