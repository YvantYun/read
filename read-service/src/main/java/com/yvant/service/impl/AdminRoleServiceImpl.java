package com.yvant.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yvant.mapper.admin.AdminRoleMapper;
import com.yvant.model.admin.AdminRole;
import com.yvant.service.IAdminRoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台用户和角色关系表 服务实现类
 * </p>
 *
 * @author yunfeng
 * @since 2019-12-25
 */
@Service
public class AdminRoleServiceImpl extends ServiceImpl<AdminRoleMapper, AdminRole> implements IAdminRoleService {

}
