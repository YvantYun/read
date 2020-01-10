package com.yvant.mapper.admin;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.yvant.model.admin.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * <p>
 * 后台用户权限表 Mapper 接口
 * </p>
 *
 * @author yunfeng
 * @since 2019-12-25
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    /**
     * 获取用户所有权限(包括+-权限)
     */
    List<Permission> getPermissionList(@Param("adminId") Long adminId);




}
