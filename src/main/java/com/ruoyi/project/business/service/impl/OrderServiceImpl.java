package com.ruoyi.project.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.enums.OrderStatus;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.random.RandomUtils;
import com.ruoyi.common.verify.wechat.vo.WeChatCreateOrderVO;
import com.ruoyi.project.business.domain.Order;
import com.ruoyi.project.business.mapper.OrderMapper;
import com.ruoyi.project.business.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService{
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private RandomUtils randomUtils;
    @Override
    public String createOrder(WeChatCreateOrderVO weChatCreateOrderVO) {
            if (weChatCreateOrderVO == null) {
                throw new ServiceException("下单参数不能为空");
            }
            Order order = Order.builder()
                    .openId(weChatCreateOrderVO.getPayer().getOpenId())
                    .orderNo("T" + randomUtils.generateNumeric(11))
                    .amount(BigDecimal.valueOf(weChatCreateOrderVO.getAmount().getTotal()).divide(BigDecimal.valueOf(100)))
                    .subject(weChatCreateOrderVO.getDescription())
                    .orderStatus(OrderStatus.WAIT_PAY.getCode())
                    .orderStatusDesc(OrderStatus.WAIT_PAY.getStatus())
                    .build();
            int result = orderMapper.insert(order);
            if (result <= 0) {
                throw new ServiceException("创建订单失败,请重试");
            } else {
                return "订单创建成功";
            }
        
    }
}
