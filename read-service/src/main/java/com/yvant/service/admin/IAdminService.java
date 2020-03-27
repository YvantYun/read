package com.yvant.service.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yvant.model.admin.Admin;
import com.yvant.model.admin.Resource;
import com.yvant.model.admin.Role;
import com.yvant.model.admin.bo.AdminRegisterBO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

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

    /**
     * 刷新token
     * @param token 旧token
     * @return 新token
     */
    String refreshToken(String token);

    /**
     * 根据用户名和姓名进行分页查询
     *
     * @param keyword
     * @param pageNum
     * @param pageSize
     * @return
     */
    IPage<Admin> getAdminList(String keyword, Integer pageNum, Integer pageSize);

    /**
     * 根据管理员id获取角色列表
     * @param adminId
     * @return
     */
    List<Role> getRoleList(Long adminId);

    /**
     * 更新指定用户信息
     * @param id
     * @param admin
     * @return
     */
    int updateAdmin(Long id, Admin admin);

    /**
     * 修改用户角色关系
     */
    @Transactional
    int updateRole(Long adminId, List<Long> roleIds);
}
