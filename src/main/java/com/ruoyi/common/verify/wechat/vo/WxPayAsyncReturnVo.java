package com.ruoyi.common.verify.wechat.vo;



import com.ruoyi.common.verify.wechat.wxenum.WxAsyncResponseEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WxPayAsyncReturnVo {
    private String code;
    private String message;

    public WxPayAsyncReturnVo(WxAsyncResponseEnum wxAsyncResponseEnum) {
        this.code = wxAsyncResponseEnum.getCode();
        this.message = wxAsyncResponseEnum.getMessage();
    }
}

