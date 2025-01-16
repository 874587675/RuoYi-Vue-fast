package com.ruoyi.project.business.verify.aliyun.pay.pc.paybusiness.controller;

import com.alipay.api.AlipayApiException;
import com.ruoyi.framework.web.domain.R;
import com.ruoyi.project.business.verify.aliyun.pay.pc.paybusiness.service.impl.AliPayPCService;
import com.ruoyi.project.business.verify.aliyun.pay.vo.AliPayPayVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/pay/alipay")
@Api(tags = "支付宝支付管理模块")
public class AliPayController {
    
    @Resource
    private AliPayPCService aliPayPCService;
    @PostMapping("/pc/pay")
    @ApiOperation("支付宝PC网页环境支付订单")
    public R<String> pay(@RequestBody AliPayPayVO aliPayPayVO) throws AlipayApiException {
        return R.ok(aliPayPCService.pay(aliPayPayVO));
    }
}
