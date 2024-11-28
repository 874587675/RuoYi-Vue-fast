package com.ruoyi.project.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.framework.security.LoginUser;
import com.ruoyi.framework.security.service.SysPasswordService;
import com.ruoyi.project.business.domain.User;
import com.ruoyi.project.business.service.UserService;
import com.ruoyi.project.system.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @program:
 * @ClassName:
 * @description:
 * @author: zgc
 * @date:
 * @Version 1.0
 **/
@Component("StudentDetailsServiceImpl")
public class StudentDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private SysPasswordService passwordService;

    @Resource
    private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("过来了！");
//        User user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
//        if(user == null){
//            throw new UsernameNotFoundException("用户名或密码错误");
//        }
//        passwordService.validate(user);
//        return createLoginUser(user);
        return null;
    }
    
    public UserDetails createLoginUser(User user) {
        return new LoginUser(Long.valueOf(user.getId()), user);
    }
}
