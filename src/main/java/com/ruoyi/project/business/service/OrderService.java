package com.ruoyi.project.business.service;

import com.ruoyi.common.verify.wechat.vo.WeChatCreateOrderVO;
import com.ruoyi.project.business.domain.Order;
import com.baomidou.mybatisplus.extension.service.IService;
public interface OrderService extends IService<Order>{


    String createOrder(WeChatCreateOrderVO weChatCreateOrderVO);
}
