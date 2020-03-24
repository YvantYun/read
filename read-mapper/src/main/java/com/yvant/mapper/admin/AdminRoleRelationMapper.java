package com.yvant.mapper.admin;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yvant.model.admin.AdminRoleRelation;
import com.yvant.model.admin.Resource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminRoleRelationMapper extends BaseMapper<AdminRoleRelation> {

    /**
     * 获取用户所有可访问资源
     */
    List<Resource> getResourceList(@Param("adminId") Long adminId);
}
