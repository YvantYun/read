package com.yvant.mapper.admin;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yvant.model.admin.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 后台用户角色表 Mapper 接口
 * </p>
 *
 * @author yunfeng
 * @since 2019-12-25
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 根据用户id 查询其角色
     * @param adminId
     * @return
     */
    List<Role> getRoleListByAdminId(@Param("adminId") Long adminId);

}
