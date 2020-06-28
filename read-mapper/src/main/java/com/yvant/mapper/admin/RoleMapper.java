package com.yvant.mapper.admin;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yvant.model.admin.Resource;
import com.yvant.model.admin.Role;
import com.yvant.model.admin.entity.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 根据adminId获取菜单列表
     *
     * @param adminId
     * @return
     */
    List<Menu> getMenuListByAdminId(@Param("adminId") Long adminId);

    /**
     * 根据roleId获取菜单列表
     *
     * @param roleId
     * @return
     */
    List<Menu> getMenuListByRoleId(@Param("roleId") Long roleId);

    /**
     * 根据roleId获取后台资源列表
     *
     * @param roleId
     * @return
     */
    List<Resource> getResourceListByRoleId(@Param("roleId") Long roleId);
}