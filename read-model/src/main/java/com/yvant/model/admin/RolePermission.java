package com.yvant.model.admin;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yvant.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


/**
 * <p>
 * 后台用户角色和权限关系表
 * </p>
 *
 * @author yunfeng
 * @since 2019-12-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_role_permission")
public class RolePermission extends BaseEntity<Long> {

    private static final long serialVersionUID = 1L;

    private Long roleId;

    private Long permissionId;


}
