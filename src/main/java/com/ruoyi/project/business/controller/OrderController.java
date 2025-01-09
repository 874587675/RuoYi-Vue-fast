package com.ruoyi.project.business.controller;

import com.ruoyi.common.enums.OrderStatus;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.verify.wechat.vo.WeChatCreateOrderVO;
import com.ruoyi.framework.web.domain.R;
import com.ruoyi.project.business.domain.Order;
import com.ruoyi.project.business.service.OrderService;
import com.ruoyi.project.business.verify.wechat.pay.pc.paybusiness.service.impl.WxPayJsapiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

@RestController
@RequestMapping("/order")
@Api(tags = "订单管理模块")
public class OrderController {

    @Resource
    private OrderService orderService;
    
    @ApiOperation("新建订单信息")
    @PostMapping("/createOrder")
    public R<String> createOrder(WeChatCreateOrderVO weChatCreateOrderVO){
        return R.ok(orderService.createOrder(weChatCreateOrderVO));
    }
}
