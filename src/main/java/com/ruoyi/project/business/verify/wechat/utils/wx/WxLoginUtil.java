package com.ruoyi.project.business.verify.wechat.utils.wx;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import me.chanjar.weixin.common.error.WxErrorException;

//微信授权登陆util
public class WxLoginUtil {

    private static WxMaService wxMaService() {
        WxMaDefaultConfigImpl config = new WxMaDefaultConfigImpl();
        config.setAppid(WxConstant.LOGIN_APPID);
        config.setSecret(WxConstant.LOGIN_SECRET);
        config.setMsgDataFormat("JSON");
        WxMaService wxMaService = new WxMaServiceImpl();
        wxMaService.setWxMaConfig(config);
        return wxMaService;
    }

    public static String returnOpenid(String openidCode) {
        try {
            return wxMaService().jsCode2SessionInfo(openidCode).getOpenid();
        } catch (WxErrorException e) {
            throw new RuntimeException(e);
        }
    }

    public static String returnTel(String telCode) {
        try {
            return wxMaService().getUserService().getPhoneNoInfo(telCode).getPhoneNumber();
        } catch (WxErrorException e) {
            throw new RuntimeException(e);
        }
    }
}
