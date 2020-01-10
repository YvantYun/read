package com.yvant.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yvant.model.admin.Permission;
import com.yvant.model.admin.Role;
import com.yvant.model.admin.dto.admin.PermissionNode;

import java.util.List;

/**
 * <p>
 * 后台用户权限表 服务类
 * </p>
 *
 * @author yunfeng
 * @since 2019-12-25
 */
public interface IPermissionService extends IService<Permission> {


    /**
     * 以层级结构返回所有权限
     */
    List<PermissionNode> treeList();

    /**
     * 获取用户权限列表
     */
    List<Permission> getPermissionList(Long adminId);

    /**
     * 获取所有的权限列表
     */
    List<Permission> getAllPermissionList();
}
