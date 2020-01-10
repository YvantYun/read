package com.yvant.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yvant.mapper.admin.RoleMapper;
import com.yvant.model.admin.Role;
import com.yvant.service.IRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 后台用户角色表 服务实现类
 * </p>
 *
 * @author yunfeng
 * @since 2019-12-25
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Override
    public List<Role> getRoleListByAdminId(Long adminId) {

        return this.baseMapper.getRoleListByAdminId(adminId);

    }
}
