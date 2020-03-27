package com.yvant.service.admin.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yvant.mapper.admin.RoleMenuRelationMapper;
import com.yvant.mapper.admin.RoleResourceRelationMapper;
import com.yvant.model.admin.RoleMenuRelation;
import com.yvant.model.admin.RoleResourceRelation;
import com.yvant.service.admin.IRoleMenuRelationService;
import com.yvant.service.admin.IRoleResourceRelationService;
import org.springframework.stereotype.Service;

/**
 * 后台角色和资源关系表 Service接口实现类
 *
 * @author yunfeng
 * @Description Created on 2020-03-26
 */
@Service
public class RoleResourceRelationServiceImpl extends ServiceImpl<RoleResourceRelationMapper, RoleResourceRelation> implements IRoleResourceRelationService {

}