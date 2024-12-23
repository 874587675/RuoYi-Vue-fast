package com.ruoyi.project.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.project.business.domain.Order;
import com.ruoyi.project.business.mapper.OrderMapper;
import com.ruoyi.project.business.service.OrderService;
import org.springframework.stereotype.Service;
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService{

}
