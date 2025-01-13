package com.ruoyi.project.business.verify.wechat.utils.wx;

import com.github.binarywang.wxpay.bean.notify.WxPayNotifyV3Result;
import com.sxkf.takeaway.service.OrderService;
import com.sxkf.takeaway.service.RechargeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/api/wx")
public class WxPayController {
    @Resource
    private OrderService orderService;
    @Resource
    private RechargeService rechargeService;

    @PostMapping(value = "/order")
    public String order(HttpServletRequest request){
        WxPayNotifyV3Result.DecryptNotifyResult decryptNotifyResult = WxPayUtils.notifyV3Result(request);
        orderService.paySuccess(decryptNotifyResult.getOutTradeNo(),"1");
        return "SUCCESS";
    }

    @PostMapping(value = "/vip")
    public String vip(HttpServletRequest request){
        WxPayNotifyV3Result.DecryptNotifyResult decryptNotifyResult = WxPayUtils.notifyV3Result(request);
        rechargeService.paySuccess(decryptNotifyResult.getOutTradeNo(),"1");
        return "SUCCESS";
    }
}
