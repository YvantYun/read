package com.yvant.mapper.admin;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yvant.model.admin.AdminRoleRelation;
import com.yvant.model.admin.Resource;
import com.yvant.model.admin.Role;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminRoleRelationMapper extends BaseMapper<AdminRoleRelation> {

    /**
     * 获取用户所有可访问资源
     */
    List<Resource> getResourceList(@Param("adminId") Long adminId);

    /**
     * 获取 角色列表
     *
     * @param adminId
     * @return
     */
    List<Role> getRoleList(@Param("adminId") Long adminId);

    /**
     * 批量插入admin和角色关系
     *
     * @param adminRoleList
     * @return
     */
    Integer insertList(@Param("list") List<AdminRoleRelation> adminRoleList);
}
