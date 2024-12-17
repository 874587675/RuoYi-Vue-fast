package com.ruoyi.project.business.service;

import com.ruoyi.project.business.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.concurrent.ExecutionException;

/**
 * @program: RuoYi-Vue-fast
 * @ClassName UserService
 * @description:
 * @author: zgc
 * @date: 2024-11-22 15:15
 * @Version 1.0
 **/
public interface UserService extends IService<User> {
    String loginByUsername(String username, String password, String code, String uuid);

    String loginByPhone(String phone, String code);
}
