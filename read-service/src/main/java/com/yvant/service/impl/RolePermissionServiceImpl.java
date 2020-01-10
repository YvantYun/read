package com.yvant.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yvant.mapper.admin.RolePermissionMapper;
import com.yvant.model.admin.RolePermission;
import com.yvant.service.IRolePermissionService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台用户角色和权限关系表 服务实现类
 * </p>
 *
 * @author yunfeng
 * @since 2019-12-25
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements IRolePermissionService {

}
