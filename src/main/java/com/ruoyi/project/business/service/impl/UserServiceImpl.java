package com.ruoyi.project.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.framework.security.service.SysPasswordService;
import com.ruoyi.project.business.domain.User;
import com.ruoyi.project.business.mapper.UserMapper;
import com.ruoyi.project.business.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
/**
* @program: RuoYi-Vue-fast
* @ClassName UserServiceImpl
* @description: 
* @author: zgc
* @date: 2024-11-22 15:15
* @Version 1.0
**/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
