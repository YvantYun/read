package com.yvant.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yvant.model.admin.Role;

import java.util.List;

/**
 * <p>
 * 后台用户角色表 服务类
 * </p>
 *
 * @author yunfeng
 * @since 2019-12-25
 */
public interface IRoleService extends IService<Role> {

    /**
     * 根据用户名id 查询其角色名称
     * @param id
     * @return
     */
    List<Role> getRoleListByAdminId(Long id);
}
