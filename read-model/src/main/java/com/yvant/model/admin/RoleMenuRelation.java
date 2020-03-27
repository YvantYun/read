package com.yvant.model.admin;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yvant.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 后台角色菜单关系表 实体类
 *
 * @author yunfeng
 * @Description Created on 2020-03-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_sys_role_menu_relation")
public class RoleMenuRelation extends BaseEntity<Long> {

    /**
     * 角色ID
     */
    private Long roleId;


    /**
     * 菜单ID
     */
    private Long menuId;


}