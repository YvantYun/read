package com.yvant.service.admin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yvant.mapper.admin.RoleMapper;
import com.yvant.model.admin.Resource;
import com.yvant.model.admin.Role;
import com.yvant.model.admin.RoleMenuRelation;
import com.yvant.model.admin.RoleResourceRelation;
import com.yvant.model.admin.entity.Menu;
import com.yvant.service.admin.IRoleMenuRelationService;
import com.yvant.service.admin.IRoleResourceRelationService;
import com.yvant.service.admin.IRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 后台用户角色表 Service接口实现类
 *
 * @author yunfeng
 * @Description Created on 2020-03-24
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    private IRoleMenuRelationService roleMenuRelationService;

    @Autowired
    private IRoleResourceRelationService roleResourceRelationService;

    @Override
    public List<Menu> getMenuListByAdminId(Long adminId) {
        return this.baseMapper.getMenuListByAdminId(adminId);
    }

    @Override
    public IPage<Role> getRoleList(String keyword, Integer pageNum, Integer pageSize) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(keyword)) {
            queryWrapper.like("name", keyword);
        }
        Page<Role> page = new Page<>(pageNum, pageSize);
        return this.baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    public int updateRole(Long id, Role role) {
        role.setId(id);
        return this.baseMapper.updateById(role);
    }

    @Override
    public int delete(List<Long> ids) {
        return this.baseMapper.deleteBatchIds(ids);
    }

    @Override
    public List<Menu> getMenuListByRoleId(Long roleId) {
        return this.baseMapper.getMenuListByRoleId(roleId);
    }

    @Override
    public int allocMenu(Long roleId, List<Long> menuIds) {
        // 删除原有的role——menu关系
        UpdateWrapper<RoleMenuRelation> wrapper = new UpdateWrapper<>();
        wrapper.eq("role_id", roleId);
        roleMenuRelationService.remove(wrapper);
        // 批量插入
        List<RoleMenuRelation> roleMenuList = menuIds.stream()
                .map(menuId -> covertRoleMenu(menuId, roleId)).collect(Collectors.toList());
        roleMenuRelationService.saveBatch(roleMenuList);
        return menuIds.size();
    }

    @Override
    public List<Resource> listResource(Long roleId) {
        return this.baseMapper.getResourceListByRoleId(roleId);
    }

    @Override
    public int allocResource(Long roleId, List<Long> resourceIds) {
        // 删除原有的role——resource关系
        UpdateWrapper<RoleResourceRelation> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("role_id", roleId);
        roleResourceRelationService.remove(updateWrapper);
        List<RoleResourceRelation> roleResourceList = resourceIds.stream()
                .map(resourceId -> covertRoleResource(resourceId, roleId))
                .collect(Collectors.toList());
        roleResourceRelationService.saveBatch(roleResourceList);
        return resourceIds.size();
    }

    private RoleMenuRelation covertRoleMenu(Long menuId, Long roleId) {
        RoleMenuRelation roleMenu = new RoleMenuRelation();
        roleMenu.setRoleId(roleId);
        roleMenu.setMenuId(menuId);
        return roleMenu;
    }

    private RoleResourceRelation covertRoleResource(Long resourceId, Long roleId) {
        RoleResourceRelation roleResource = new RoleResourceRelation();
        roleResource.setResourceId(resourceId);
        roleResource.setRoleId(roleId);
        return roleResource;
    }
}
