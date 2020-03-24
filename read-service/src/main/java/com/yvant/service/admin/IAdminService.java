package com.yvant.service.admin;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yvant.model.admin.Admin;
import com.yvant.model.admin.Resource;
import com.yvant.model.admin.bo.AdminRegisterBO;
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
     * 登录功能
     * @param username 用户名
     * @param password 密码
     * @return 生成的JWT的token
     */
    String login(String username, String password);

    /**
     * 获取用户信息
     */
    UserDetails loadUserByUsername(String username);

    /**
     * 根据用户名获取后台管理员
     */
    Admin getAdminByUsername(String username);

    /**
     * 获取指定用户的可访问资源
     */
    List<Resource> getResourceList(Long adminId);

    /**
     * 注册功能
     */
    Admin register(AdminRegisterBO registerBO);
}
