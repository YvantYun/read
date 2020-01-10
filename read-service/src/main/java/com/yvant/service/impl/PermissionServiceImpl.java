package com.yvant.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.yvant.mapper.admin.PermissionMapper;
import com.yvant.model.admin.Permission;
import com.yvant.model.admin.dto.admin.PermissionNode;
import com.yvant.service.IPermissionService;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 后台用户权限表 服务实现类
 * </p>
 *
 * @author yunfeng
 * @since 2019-12-25
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

    @Override
    public List<PermissionNode> treeList() {
        // 先查询该用户所有的权限
        List<Permission> permissionList = getPermissionList(null);
        //过滤
        List<Permission> distinctList = permissionList.stream().distinct().collect(Collectors.toList());
        List<PermissionNode> result = permissionList.stream()
                .filter(permission -> permission.getPid().equals(0L))
                .map(permission -> covert(permission, distinctList)).collect(Collectors.toList());
        return result;

    }

    @Override
    public List<Permission> getPermissionList(Long adminId) {
        List<Permission> permissionList = this.baseMapper.getPermissionList(adminId);
        return permissionList;
    }

    @Override
    public List<Permission> getAllPermissionList() {
        LambdaQueryWrapper<Permission> query = new LambdaQueryWrapper<>();
        query.eq(Permission::getStatus, 1).eq(Permission::getDeleted, 0);
        return this.baseMapper.selectList(query);
    }

    private PermissionNode covert(Permission permission, List<Permission> permissionList) {

        PermissionNode node = new PermissionNode();
        BeanUtils.copyProperties(permission,node);
        List<PermissionNode> children = permissionList.stream()
                .filter(subPermission -> subPermission.getPid().equals(permission.getId()))
                .filter(subPermission -> subPermission.getType().equals(2))
                .map(subPermission -> covert(subPermission,permissionList)).collect(Collectors.toList());
        node.setChildren(children);
        return node;
    }
}
