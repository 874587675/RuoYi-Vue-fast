package com.ruoyi.project.business.verify.aliyun.pay.paybusiness.controller;

import com.alipay.api.AlipayApiException;
import com.ruoyi.framework.web.domain.R;
import com.ruoyi.project.business.verify.aliyun.pay.paybusiness.service.impl.AliPayPCService;
import com.ruoyi.project.business.verify.aliyun.vo.AliPayTradeCloseVO;
import com.ruoyi.project.business.verify.aliyun.vo.AliPayTradePayVO;
import com.ruoyi.project.business.verify.aliyun.vo.AliPayTradeQueryVO;
import com.ruoyi.project.business.verify.aliyun.vo.AliPayTradeVO;
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
public class AliPayPCController {
    
    @Resource
    private AliPayPCService aliPayPCService;
    @PostMapping("/pc/pay")
    @ApiOperation("支付宝PC网页环境支付订单")
    public R<String> pay(@RequestBody AliPayTradePayVO aliPayTradePayVO) throws AlipayApiException {
        return R.ok(aliPayPCService.pay(aliPayTradePayVO));
    }
    
    @PostMapping("/pc/close")
    @ApiOperation("关闭支付宝PC网页环境支付订单")
    public R<String> close(@RequestBody AliPayTradeCloseVO aliPayTradeCloseVO) throws AlipayApiException {
        return R.ok(aliPayPCService.close(aliPayTradeCloseVO));
    }
    
    @PostMapping("/pc/query")
    @ApiOperation("查询支付宝PC网页环境支付订单")
    public R<String> query(@RequestBody AliPayTradeQueryVO aliPayTradeQueryVO) throws AlipayApiException {
        return R.ok(aliPayPCService.query(aliPayTradeQueryVO));
    }
}
