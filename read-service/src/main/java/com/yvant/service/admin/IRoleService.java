package com.yvant.service.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yvant.model.admin.Resource;
import com.yvant.model.admin.Role;
import com.yvant.model.admin.entity.Menu;

import java.util.List;

/**
 * 后台用户角色表 Service接口
 *
 * @author yunfeng
 * @Description Created on 2020-03-24
 */
public interface IRoleService extends IService<Role> {

    /**
     * 根据管理员ID获取对应菜单
     */
    List<Menu> getMenuListByAdminId(Long adminId);

    /**
     * 获取角色列表 ——分页
     */
    IPage<Role> getRoleList(String keyword, Integer pageNum, Integer pageSize);

    /**
     * 更新角色信息
     *
     * @param id
     * @param role
     * @return
     */
    int updateRole(Long id, Role role);

    /**
     * 批量删除角色信息
     *
     * @param ids
     * @return
     */
    int delete(List<Long> ids);

    /**
     * 根据角色id 获取所有菜单
     *
     * @param roleId
     * @return
     */
    List<Menu> getMenuListByRoleId(Long roleId);

    /**
     * 给角色分配菜单
     *
     * @param roleId
     * @param menuIds
     * @return
     */
    int allocMenu(Long roleId, List<Long> menuIds);

    /**
     * 根据角色获取资源列表
     *
     * @param roleId
     * @return
     */
    List<Resource> listResource(Long roleId);

    /**
     * 给角色分资源
     *
     * @param roleId
     * @param resourceIds
     * @return
     */
    int allocResource(Long roleId, List<Long> resourceIds);
}