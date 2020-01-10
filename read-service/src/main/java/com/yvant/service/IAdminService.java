package com.yvant.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yvant.model.admin.Admin;
import com.yvant.model.admin.Permission;
import com.yvant.model.admin.dto.admin.AdminParam;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * <p>
 * 后台用户表 服务类
 * </p>
 *
 * @author yunfeng
 * @since 2019-12-25
 */
public interface IAdminService extends IService<Admin> {
    /**
     * 根据用户名获取后台管理员
     */
    Admin getAdminByUsername(String username);

    /**
     * 获取用户信息
     */
    UserDetails loadUserByUsername(String username);

    /**
     * 获取用户所有权限（包括角色权限和+-权限）
     */
    List<Permission> getPermissionList(Long adminId);

    /**
     * 用户注册
     */
    Admin register(AdminParam adminParam);

    /**
     * 用户登录返回token
     */
    String login(String username, String password);

    /**
     * 用户刷新token
     * @param token
     */
    String refreshToken(String token);

}
