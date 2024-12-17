package com.ruoyi.framework.security.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.project.business.domain.User;
import com.ruoyi.project.business.mapper.UserMapper;
import com.ruoyi.project.system.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * 用户验证处理
 *
 * @author ruoyi
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysPasswordService passwordService;

    @Autowired
    private SysPermissionService permissionService;
    
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) {
        System.out.println("我来了");
//        SysUser user = sysUserService.selectUserByUserName(username);
//        if (StringUtils.isNull(user)) {
//            log.info("登录用户：{} 不存在.", username);
//            throw new ServiceException(MessageUtils.message("user.not.exists"));
//        } else if (UserStatus.DELETED.getCode().equals(user.getDelFlag())) {
//            log.info("登录用户：{} 已被删除.", username);
//            throw new ServiceException(MessageUtils.message("user.password.delete"));
//        } else if (UserStatus.DISABLE.getCode().equals(user.getStatus())) {
//            log.info("登录用户：{} 已被停用.", username);
//            throw new ServiceException(MessageUtils.message("user.blocked"));
//        }
//        passwordService.validate(user);
//        return createLoginUser(user);

        User user1 = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
        passwordService.validate(user1);
        return user1;
        
    }
    
//    public UserDetails createLoginUser(SysUser user) {
//        return new LoginUser(user.getUserId(), user.getDeptId(), user, permissionService.getMenuPermission(user));
//    }

}
